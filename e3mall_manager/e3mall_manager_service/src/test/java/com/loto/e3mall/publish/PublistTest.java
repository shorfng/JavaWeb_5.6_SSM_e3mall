package com.loto.e3mall.publish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author：蓝田_Loto
 * Date：2019-04-17 23:33
 * PageName：PublistTest.java
 * Function：服务的发布与Tomcat的启动没有关系
 */

public class PublistTest {
    @Test
    public void PublistService() throws Exception {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        System.out.println("服务已经启动...");
        System.in.read();  // 等待键 盘输入
        System.out.println("服务已经关闭");
    }
}
