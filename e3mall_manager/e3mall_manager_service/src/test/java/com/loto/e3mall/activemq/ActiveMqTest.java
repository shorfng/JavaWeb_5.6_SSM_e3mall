package com.loto.e3mall.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

/**
 * Author：蓝田_Loto
 * Date：2019-06-14 13:05
 * PageName：ActiveMqTest.java
 * Function：ActiveMQ的消息传递形式（点对点模式、发布/订阅模式）
 */

public class ActiveMqTest {
    // 点到点模式：发送消息（queue-Producer）
    @Test
    public void testQueueProducer() throws Exception {
        // 1、创建一个连接工厂对象（需要指定服务的ip及端口61616）
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.31.130:61616");

        // 2、使用工厂对象创建一个Connection对象
        Connection connection = connectionFactory.createConnection();

        // 3、开启连接（调用Connection对象的start方法）
        connection.start();

        // 4、创建一个Session对象
        // 参数1：是否开启事务（如果true开启事务，第二个参数无意义，一般不开启事务false）
        // 参数2：应答模式（自动应答或者手动应答，一般自动应答）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5、使用Session对象创建一个Destination对象（两种形式queue、topic，现在应该使用queue）
        Queue queue = session.createQueue("test-queue");

        // 6、使用Session对象创建一个Producer生产者对象
        MessageProducer producer = session.createProducer(queue);

        // 7、创建一个Message消息对象
        //TextMessage textMessage = new ActiveMQTextMessage();
        //textMessage.setText("activemq：queue message！");
        TextMessage textMessage = session.createTextMessage("activemq：queue message！");

        // 8、发送消息
        producer.send(textMessage);

        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    // 点到点模式：接收消息（queue-Consumer）
    @Test
    public void testQueueConsumer() throws Exception {
        // 创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.31.130:61616");

        // 创建一个连接对象
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个Destination对象（queue对象）
        Queue queue = session.createQueue("test-queue");

        // 使用Session对象创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(queue);

        // 接收消息
        consumer.setMessageListener(new MessageListener() {
            // 匿名内部类
            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 等待接收消息
        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    // 发布/订阅模式：发送消息（Topic-Producer）
    @Test
    public void testTopicProducer() throws Exception {
        // 1、创建一个连接工厂对象（需要指定服务的ip及端口61616）
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.31.130:61616");

        // 2、使用工厂对象创建一个Connection对象
        Connection connection = connectionFactory.createConnection();

        // 3、开启连接（调用Connection对象的start方法）
        connection.start();

        // 4、创建一个Session对象
        // 参数1：是否开启事务（如果true开启事务，第二个参数无意义，一般不开启事务false）
        // 参数2：应答模式（自动应答或者手动应答，一般自动应答）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5、使用Session对象创建一个Destination对象（两种形式queue、topic，现在应该使用topic）
        Topic topic = session.createTopic("test-topic");

        // 6、使用Session对象创建一个Producer生产者对象
        MessageProducer producer = session.createProducer(topic);

        // 7、创建一个Message消息对象
        //TextMessage textMessage = new ActiveMQTextMessage();
        //textMessage.setText("activemq：topic message！");
        TextMessage textMessage = session.createTextMessage("activemq：topic message！");

        // 8、发送消息
        producer.send(textMessage);

        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    // 发布/订阅模式：接收消息（Topic-Consumer）
    @Test
    public void testTopicConsumer() throws Exception {
        // 创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.31.130:61616");

        // 创建一个连接对象
        Connection connection = connectionFactory.createConnection();

        //开启连接
        connection.start();

        // 使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 创建一个Destination对象。topic对象
        Topic topic = session.createTopic("test-topic");

        // 使用Session对象创建一个消费者对象。
        MessageConsumer consumer = session.createConsumer(topic);

        // 接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("topic消费者1启动。。。。");

        // 等待接收消息
        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
