package com.loto.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Author：蓝田_Loto
 * Date：2019-06-14 14:22
 * PageName：ActiveMqSpringTest.java
 * Function：消息的提供者（发消息）
 */

public class ActiveMqProducerTest {
    @Test
    public void sendMessage() throws Exception {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");

        // 从容器中获得JmsTemplate对象
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        // 从容器中获得一个Destination对象（applicationContext-activemq.xml配置中有）（此处配置的为发布/订阅模式，必须先有消费者，发送才能成功）
        Destination destination = (Destination) applicationContext.getBean("queueDestination");

        // 发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("activemq的消息内容");
            }
        });
    }
}
