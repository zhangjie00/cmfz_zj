package com.baizhi.service.impl;


import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.ChapterExample;
import com.baizhi.service.ChapterService;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.RowBounds;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional

public class ChapterServiceImpl implements ChapterService {


    @Resource
    ChapterDao chapterMapper;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows, String albumId) {
        HashMap<String, Object> map = new HashMap<>();

        //总条数  records
        ChapterExample example = new ChapterExample();
        Integer records = chapterMapper.selectCountByExample(example);

        map.put("records",records);

        //总页数  total
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("total",total);

        //当前页  page
        map.put("page",page);

        //数据  rows
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);

        example.createCriteria().andAlbumIdEqualTo(albumId);
        List<Chapter> chapters = chapterMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",chapters);

        return map;
    }

    @Override
    public String add(Chapter chapter) {

        String uuid = UUIDUtil.getUUID();
        chapter.setId(uuid);
        chapter.setUploadTime(new Date());

        System.out.println("=service==chapter:"+chapter);
        chapterMapper.insertSelective(chapter);

        return uuid;
    }

    @Override
    public void update(Chapter chapter) {


    }

    @Override
    public void uploadChapter(MultipartFile src, String id, HttpServletRequest request) {

        //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");

        //判断文件件是否存在
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }

        //获取上传的文件名
        String filename = src.getOriginalFilename();

        //给文件名加一个事件戳
        String newName=new Date().getTime()+"-"+filename;

        //文件上传
        try {
            src.transferTo(new File(realPath,newName));

            //获取文件大小   B 字节  KB MB GB TB   1024
            long size = src.getSize();
            String s = String.valueOf(size);
            Double aDouble = Double.valueOf(size)/1024/1024;
            DecimalFormat format = new DecimalFormat("0.00");
            String sizes = format.format(aDouble)+"MB";

            //获取文件的时长  秒
            AudioFile audioFile = AudioFileIO.read(new File(realPath, newName));
            AudioHeader audioHeader = audioFile.getAudioHeader();
            //获取的秒
            int length = audioHeader.getTrackLength();
            String duration=length/60+"分"+length%60+"秒";

            System.out.println(length);
            System.out.println(duration);


            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setSrc(newName);
            chapter.setSize(sizes);
            chapter.setDuration(duration);

            System.out.println("=service 上传文件修改=chapter"+chapter);
            ChapterExample example = new ChapterExample();
            example.createCriteria().andIdEqualTo(id);
            chapterMapper.updateByExampleSelective(chapter,example);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response) {

        //1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");

        try {
            //2.创建读入流
            FileInputStream inputStream = new FileInputStream(new File(realPath,audioName));

            //3.设置响应头   attachment:以附件的形式下载    inline:在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(audioName,"UTF-8"));

            //4.文件下载
            IOUtils.copy(inputStream,response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
