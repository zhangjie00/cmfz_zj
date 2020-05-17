package com.baizhi.service.impl;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.ArticleExample;
import com.baizhi.entity.UserExample;
import com.baizhi.repository.ArticleRepository;
import com.baizhi.service.ArticleService;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.collections4.IterableUtils;
import org.apache.ibatis.session.RowBounds;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleMapper;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        //总条数  records
        UserExample example = new UserExample();
        Integer records = articleMapper.selectCountByExample(example);

        map.put("records", records);

        //总页数  total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);

        //当前页  page
        map.put("page", page);

        //数据  rows
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleMapper.selectByRowBounds(new Article(), rowBounds);
        map.put("rows", articles);

        return map;
    }

    @Override
    public void delete(Article article) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(article.getId());
        articleMapper.deleteByExample(example);
    }

    @Override
    public void add(Article article) {

        article.setId(UUIDUtil.getUUID());
        article.setUploadTime(new Date());
        articleMapper.insertSelective(article);
    }

    @Override
    public void update(Article article) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(article.getId());
        articleMapper.updateByExampleSelective(article, example);
    }

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;



    @Override
    public List<Article> querySearchs(String content) {
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        field.preTags("<span style='color:red'>"); //前缀
        field.postTags("</span>"); //后缀

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("cmfz") //指定索引名
                .withTypes("article")  //指定索引类型
                .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("content")) //搜索条件
                .withHighlightFields(field)//处理高亮
                //.withFields("id","title","content")
                .build();

        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(searchQuery, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                ArrayList<Article> articleList = new ArrayList<>();

                //获取查询结果
                SearchHit[] hits = searchResponse.getHits().getHits();

                for (SearchHit hit : hits) {

                    //处理数据  获取数据的集合
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                    String id=sourceAsMap.get("id")!=null?sourceAsMap.containsKey("id")?sourceAsMap.get("id").toString():null:null;
                    String title=sourceAsMap.get("title")!=null?sourceAsMap.containsKey("title")?sourceAsMap.get("title").toString():null:null;
                    String guruName=sourceAsMap.get("guruName")!=null?sourceAsMap.containsKey("guruName")?sourceAsMap.get("guruName").toString():null:null;
                    String guruId=sourceAsMap.get("guruId")!=null?sourceAsMap.containsKey("guruId")?sourceAsMap.get("guruId").toString():null:null;
                    String content=sourceAsMap.get("content")!=null?sourceAsMap.containsKey("content")?sourceAsMap.get("content").toString():null:null;

                    Date uploadTime = null;
                    if(sourceAsMap.get("uploadTime")!=null){
                        boolean times = sourceAsMap.containsKey("uploadTime");
                        if(times){
                            String uploadTimes = sourceAsMap.get("uploadTime").toString();
                            //处理日期操作
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                            try {
                                uploadTime = dateFormat.parse(uploadTimes);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    Article article = new Article(id,title,uploadTime,guruName,guruId,content);

                    //处理高亮  获取高亮的集合
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                    //判断title 不等于null才处理高亮
                    if(title!=null){
                        if(highlightFields.get("title")!=null){
                            HighlightField title1 = highlightFields.get("title");
                            String titles = highlightFields.get("title").fragments()[0].toString();
                            article.setTitle(titles);
                        }
                    }

                    if(content!=null){
                        if(highlightFields.get("content")!=null){
                            String contents = highlightFields.get("content").fragments()[0].toString();
                            article.setContent(contents);
                        }
                    }

                    System.out.println(article);
                    //将封装好的对象放入集合
                    articleList.add(article);
                }

                //强转 返回
                return new AggregatedPageImpl<T>((List<T>) articleList);
            }
        });
        List<Article> content1 = articles.getContent();

        for (Article article : content1) {
            System.out.println("==="+article);
        }
        return content1;
    }
}
