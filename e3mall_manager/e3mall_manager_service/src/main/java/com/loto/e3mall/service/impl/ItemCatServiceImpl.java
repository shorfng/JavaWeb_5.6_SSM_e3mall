package com.loto.e3mall.service.impl;

// 商品分类管理
import java.util.ArrayList;
import java.util.List;

import com.loto.e3mall.common.pojo.EasyUITreeNode;
import com.loto.e3mall.mapper.TbItemCatMapper;
import com.loto.e3mall.service.ItemCatService;

import com.loto.e3mall.pojo.TbItemCat;
import com.loto.e3mall.pojo.TbItemCatExample;
import com.loto.e3mall.pojo.TbItemCatExample.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    // 根据parentId查询节点列表
    @Override
    public List<EasyUITreeNode> getItemCatlist(long parentId) {
        // 根据 parentId 查询子节点列表
        TbItemCatExample example = new TbItemCatExample();

        // 查询
        Criteria criteria = example.createCriteria();  // 创建查询
        criteria.andParentIdEqualTo(parentId);         // 设置查询条件
        List<TbItemCat> list = itemCatMapper.selectByExample(example);  // 执行查询

        // 创建返回结果List
        List<EasyUITreeNode> resultList = new ArrayList<>();

        // 把子节点列表转换成 EasyUITreeNode 列表
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();

            // 设置属性（id、text、state）
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");

            // 添加到结果列表
            resultList.add(node);
        }
        return resultList;   // 返回结果
    }

}
