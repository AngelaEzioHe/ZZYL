package com.zzyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/9 05:25
 */

@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 测试RedisTemplate
     */
    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate);
    }

    /**
     * 操作字符串类型数据
     */
    @Test
    public void testString(){
        redisTemplate.opsForValue().set("name","zhangsan");
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForValue().set("token","123abc",20, TimeUnit.SECONDS);
        //setnx
        redisTemplate.opsForValue().setIfAbsent("lock","123abc");
        redisTemplate.opsForValue().setIfAbsent("lock","456def");
    }

    /**
     * 操作hash类型数据
     */
    @Test
    public void testHash(){
        redisTemplate.opsForHash().put("user","name","张三");
        redisTemplate.opsForHash().put("user","age","18");

        System.out.println(redisTemplate.opsForHash().get("user","name"));
        System.out.println(redisTemplate.opsForHash().get("user","age"));

        System.out.println(redisTemplate.opsForHash().keys("user"));

        System.out.println(redisTemplate.opsForHash().values("user"));

        redisTemplate.opsForHash().delete("user","name");
    }

    /**
     * 操作列表类型的数据
     */
    @Test
    public void testList() {
        // 插入多个值[a,b,c]
        redisTemplate.opsForList().leftPushAll("mylist", "a", "b", "c");
        // 在列表左边插入一个值[d,a,b,c]
        redisTemplate.opsForList().leftPush("mylist", "d");
        // 获取列表中的数据
        System.out.println(redisTemplate.opsForList().range("mylist", 0, -1));
        // 从左边弹出一个，并获取值，弹出后列表中删除
        System.out.println(redisTemplate.opsForList().leftPop("mylist"));
        // 获取列表的长度
        System.out.println(redisTemplate.opsForList().size("mylist"));
    }

    /**
     * 操作集合类型的数据
     */
    @Test
    public void testSet() {
        // 添加数据
        redisTemplate.opsForSet().add("myset1", "a", "b", "c", "d");
        redisTemplate.opsForSet().add("myset2", "a", "b", "x", "y");
        // 获取集合中的所有成员
        Set<String> members = redisTemplate.opsForSet().members("myset1");
        System.out.println(members);

        // 获取集合大小
        long size = redisTemplate.opsForSet().size("myset1");
        System.out.println(size);

        // 交集
        Set<String> intersection = redisTemplate.opsForSet().intersect("myset1", "myset2");
        System.out.println("交集：" + intersection);
        // 并集
        Set<String> union = redisTemplate.opsForSet().union("myset1", "myset2");
        System.out.println("并集：" + union);
    }

    /**
     * 操作有序集合类型的数据
     */
    @Test
    public void testZset() {
        // 添加数据
        redisTemplate.opsForZSet().add("myzset", "a", 1);
        redisTemplate.opsForZSet().add("myzset", "b", 10);
        redisTemplate.opsForZSet().add("myzset", "c", 20);

        // 获取集合中的所有成员
        Set<String> members = redisTemplate.opsForZSet().range("myzset", 0, -1);
        System.out.println(members);

        // 给a成员的分数增加10
        redisTemplate.opsForZSet().incrementScore("myzset", "a", 10);

        // 删除a、b两个成员
        redisTemplate.opsForZSet().remove("myzset", "a", "b");
    }

    /**
     * 通用命令操作
     */
    @Test
    public void testCommon() {
        // 获取所有key
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);

        // 判断key是否存在
        Boolean isName = redisTemplate.hasKey("name");
        System.out.println(isName);

        // 获取key的类型
        DataType type = redisTemplate.type("myzset");
        System.out.println(type.name());

        // 删除key
        redisTemplate.delete("myzset");

    }

}
