package com.loto.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author：蓝田_Loto
 * Date：2019-06-14 16:18
 * PageName：ActiveMqConsumerTest.java
 * Function：消息的消费者（接收消息）
 */

public class ActiveMqConsumerTest {
    @Test
    public void msgConsumer() throws Exception {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");

        // 等待
        System.in.read();
    }
}
