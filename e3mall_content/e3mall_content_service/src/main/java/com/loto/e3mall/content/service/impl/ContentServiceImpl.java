package com.loto.e3mall.content.service.impl;

import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.content.service.ContentService;
import com.loto.e3mall.mapper.TbContentMapper;
import com.loto.e3mall.pojo.TbContent;
import com.loto.e3mall.pojo.TbContentExample;
import com.loto.e3mall.pojo.TbContentExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Author：蓝田_Loto
 * Date：2019-05-31 18:48
 * PageName：ContentServiceImpl.java
 * Function：内容管理实现类
 */

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    // 新增内容
    @Override
    public E3Result addContent(TbContent content) {
        // 将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());

        // 插入到数据库
        contentMapper.insert(content);
        return E3Result.ok();
    }

    // 根据内容分类id查询内容列表
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();

        // 设置查询条件
        criteria.andCategoryIdEqualTo(cid);

        // 执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }
}
