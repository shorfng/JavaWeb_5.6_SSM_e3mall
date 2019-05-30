package com.loto.e3mall.content.service.impl;

import com.loto.e3mall.common.pojo.EasyUITreeNode;
import com.loto.e3mall.content.service.ContentCategoryService;
import com.loto.e3mall.mapper.TbContentCategoryMapper;
import com.loto.e3mall.pojo.TbContentCategory;
import com.loto.e3mall.pojo.TbContentCategoryExample;
import com.loto.e3mall.pojo.TbContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：蓝田_Loto
 * Date：2019-04-29 13:24
 * PageName：ContentCategoryServiceImpl.java
 * Function：内容分类管理实现类
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    // 根据parentId查询子节点列表
    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        // 根据parentid查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();

        // 设置查询条件
        criteria.andParentIdEqualTo(parentId);

        // 执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);

        // 转换成EasyUITreeNode的列表
        List<EasyUITreeNode> nodeList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : catList) {
            EasyUITreeNode node = new EasyUITreeNode();

            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");

            // 添加到列表
            nodeList.add(node);
        }
        return nodeList;
    }

}

