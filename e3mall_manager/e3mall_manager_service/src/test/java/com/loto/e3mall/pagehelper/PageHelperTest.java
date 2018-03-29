package com.loto.e3mall.pagehelper;

import java.util.List;

import com.loto.e3mall.mapper.TbItemMapper;
import com.loto.e3mall.pojo.TbItem;
import com.loto.e3mall.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PageHelperTest {

    @Test
    public void testPageHelper() throws Exception {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");

        // 从容器中获得Mapper代理对象
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);

        // 设置分页信息：获取第1页的10条内容，默认查询总数count
        PageHelper.startPage(1, 10);

        // 执行sql查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);

        // 取分页信息：PageInfo（可以获取到总记录数、总页数、当前页码等）
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        System.out.println(pageInfo.getTotal());  // 总记录数
        System.out.println(pageInfo.getPages());  // 总页数
        System.out.println(list.size());          // 当前页的记录数
    }
}
