package com.baizhi.cache;

import com.baizhi.annotcation.AddCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Configuration
@Aspect
public class RedisCacheHashs {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate StringredisTemplate;


    @Around("@annotation(com.baizhi.annotcation.AddCache)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{

        System.out.println("===环绕通知===");

        /*序列化解决乱码*/
        StringRedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);

        // key 类的全限定名+方法名+实参   value  方法的返回值
        StringBuilder sb = new StringBuilder();

        //获取类的全限定名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        System.out.println("=============666=========="+className);

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        sb.append(methodName);

        //获取方法的所有参数
        Object[] args = proceedingJoinPoint.getArgs();

        //遍历参数
        for (Object arg : args) {
            sb.append(arg);

        }
        //拼接好的key  类的全限定名+方法名+实参
        String key = sb.toString();

        //redis hash类型的操作
        HashOperations hashOperations = redisTemplate.opsForHash();

        //判断KEY 中key在缓存中是否存在
        Boolean aBoolean = hashOperations.hasKey(className, key);

        System.out.println(aBoolean);

        Object result =null;

        //判断key是否存在
        if(aBoolean){
            //存在  从缓存中取出返回数据
            result = hashOperations.get(className,key);

        }else{
                   //放行方法                            .
            result = proceedingJoinPoint.proceed();
            //将数据加入redis缓存
            hashOperations.put(className,key,result);
        }
        return result;
    }

    @After("@annotation(com.baizhi.annotcation.DelCache)")
    public void after(JoinPoint joinPoint){

        //获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println("=====================fdff=="+className);

        //删除缓存
        redisTemplate.delete(className);
    }



}
