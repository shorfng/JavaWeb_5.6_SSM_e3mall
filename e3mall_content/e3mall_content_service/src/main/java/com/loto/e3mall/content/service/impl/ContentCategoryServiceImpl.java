package com.loto.e3mall.content.service.impl;

import com.loto.e3mall.common.pojo.EasyUITreeNode;
import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.content.service.ContentCategoryService;
import com.loto.e3mall.mapper.TbContentCategoryMapper;
import com.loto.e3mall.pojo.TbContentCategory;
import com.loto.e3mall.pojo.TbContentCategoryExample;
import com.loto.e3mall.pojo.TbContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    // 添加内容分类节点
    @Override
    public E3Result addContentCategory(long parentId, String name) {
        // 创建一个tb_content_category表对应的pojo对象
        TbContentCategory contentCategory = new TbContentCategory();

        // 设置pojo的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setStatus(1);       // 1(正常),2(删除)
        contentCategory.setSortOrder(1);    // 默认排序就是1
        contentCategory.setIsParent(false); // 新添加的节点一定是叶子节点
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());

        // 插入到数据库
        contentCategoryMapper.insert(contentCategory);

        // 根据parentid查询父节点，判断父节点的isparent属性，如果不是true改为true
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parent);  //更新到数数据库
        }
        // 返回结果，返回E3Result，包含pojo
        return E3Result.ok(contentCategory);
    }
}

