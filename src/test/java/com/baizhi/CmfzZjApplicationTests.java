package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class CmfzZjApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public  void   test1() {
        redisTemplate.opsForValue().set("nameuu", "xiaohei666");
        Object name = redisTemplate.opsForValue().get("nameuu");
        System.out.println(name);
        /*redisTemplate.opsForValue(); //操作字符串
        redisTemplate.opsForList();  //操作List
        redisTemplate.opsForSet();  //操作SET
        redisTemplate.opsForZSet(); //操作zset
        redisTemplate.opsForHash(); //操作HASH*/
    }

  /*  @Autowired
    private ArticleService articleService;
   //private AdminDao adminDao;
    @Test
    void contextLoads() {
        HashMap<String, Object> map = articleService.queryByPage(1, 2);
        System.out.println("============="+map);
*/













      /*  //根据姓名和密码查
        Admin admin = new Admin();
        admin.setUsername("kkk");
        admin.setPassword("321");
        Admin admin1 = adminDao.selectOne(admin);//返回值是对象
        System.out.println(admin1);*/

        // List<Admin> list = adminDao.selectAll();//查所有

       /* Admin admin = new Admin();
        admin.setUsername("eee");
        //根据姓名查，有重复值
        List<Admin> list = adminDao.select(admin);*/

        /*//根据id查
        Example example = new Example(Admin.class);
        example.createCriteria().andEqualTo("id","a5d03c80-3b8a-440e-aa12-2681820108be");
        List<Admin> list = adminDao.selectByExample(example);
        System.out.println(list);
        */

/*

       Example example = new Example(Admin.class);
      //example.createCriteria().andEqualTo("username","kkk");//条件,起过滤作用
       RowBounds rowBounds = new RowBounds(0,2);//分页，从第几条记录开始，每页展示几条数据
       List<Admin> list = adminDao.selectByExampleAndRowBounds(example, rowBounds);
       System.out.println(list);
*/

      /* Example example = new Example(Admin.class);
      //example.createCriteria().andEqualTo("username","nemo");
        List<Admin> list = adminDao.selectByExample(example);//条件查询
        */

      /* Admin admin = new Admin();
       admin.setId(UUID.randomUUID().toString());
       admin.setUsername("sun22");
       admin.setNickname("keke");
       admin.setPassword("311");
       //添加
       adminDao.insert(admin);*/

        /*
        //更改
        Admin admin = new Admin();
        admin.setId("a5d03c80-3b8a-440e-aa12-2681820108be");
        admin.setUsername("sun01");
        admin.setNickname("sun60");
        //没写的属性就默认为原来的值
       adminDao.updateByPrimaryKeySelective(admin);
       //没写的属性不会默认为原来的值，为null了
       adminDao.updateByPrimaryKey(admin);*/


        /*//删除,,根据姓名，id皆可
        Admin admin = new Admin();
        // admin.setId("02a8f1aa-857c-470c-95a7-3b3b5b841d08");
        admin.setUsername("kimi");
        adminDao.delete(admin);*/






}
