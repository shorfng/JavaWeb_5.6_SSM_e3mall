package com.loto.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Author：蓝田_Loto
 * Date：2019-06-06 17:23
 * PageName：JedisTest.java
 * Function：Redis在Java中的使用 - Jedis
 */

public class JedisTest {
    // 连接单机版
    @Test
    public void testJedis() throws Exception {
        // 创建一个连接Jedis对象
        Jedis jedis = new Jedis("192.168.31.130", 6379);

        // 直接使用jedis操作redis（所有jedis的命令都对应一个方法）
        jedis.set("test1", "my first jedis test");
        String string = jedis.get("test1");
        System.out.println(string);

        // 关闭连接
        jedis.close();
    }

    // 连接单机版使用连接池
    @Test
    public void testJedisPool() throws Exception {
        // 创建一个连接池对象
        JedisPool jedisPool = new JedisPool("192.168.31.130", 6379);

        // 从连接池获得一个连接（就是一个jedis对象）
        Jedis jedis = jedisPool.getResource();

        // 使用jedis操作redis
        String string = jedis.get("test1");
        System.out.println(string);

        jedis.close();     // 关闭连接（每次使用完毕后关闭连接。连接池回收资源）
        jedisPool.close(); // 关闭连接池
    }

    // 连接集群版
    @Test
    public void testJedisCluster() throws Exception {
        // 创建一个JedisCluster对象（有一个参数nodes是一个set类型，set中包含若干个HostAndPort对象）
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.31.130", 7001));
        nodes.add(new HostAndPort("192.168.31.130", 7002));
        nodes.add(new HostAndPort("192.168.31.130", 7003));
        nodes.add(new HostAndPort("192.168.31.130", 7004));
        nodes.add(new HostAndPort("192.168.31.130", 7005));
        nodes.add(new HostAndPort("192.168.31.130", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);

        // 直接使用JedisCluster对象操作redis。
        jedisCluster.set("test1", "123");
        String string = jedisCluster.get("test1");
        System.out.println(string);

        // 关闭JedisCluster对象
        jedisCluster.close();
    }
}
