package com.loto.jedis;

import com.loto.e3mall.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author：蓝田_Loto
 * Date：2019-06-06 19:48
 * PageName：.java
 * Function：测试jedis工具类配置到spring容器
 */

public class JedisClientTest {
    @Test
    public void testJedisClient() throws Exception {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");

        // 从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("mytest", "jedisClient");
        String string = jedisClient.get("mytest");

        System.out.println(string);
    }
}
