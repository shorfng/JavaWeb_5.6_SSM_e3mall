package com.loto.e3mall.service.impl;

// 商品管理Service

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.loto.e3mall.common.pojo.EasyUIDataGridResult;
import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.common.utils.IDUtils;

import com.loto.e3mall.mapper.TbItemDescMapper;
import com.loto.e3mall.mapper.TbItemMapper;

import com.loto.e3mall.pojo.TbItem;
import com.loto.e3mall.pojo.TbItemDesc;
import com.loto.e3mall.pojo.TbItemExample;
import com.loto.e3mall.pojo.TbItemExample.Criteria;

import com.loto.e3mall.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    // 根据商品id查询商品信息
    @Override
    public TbItem getItemById(long itemId) {
        // 根据主键查询
        // TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        // 根据条件查询
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId); // 设置查询条件

        // 执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    // 查询所有商品列表，进行分页处理
    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        // 设置分页信息
        PageHelper.startPage(page, rows);

        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);

        // 创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);

        // 取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        // 取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);

        return result;
    }

    // 商品添加功能
    @Override
    public E3Result addItem(TbItem item, String desc) {
        // 生成商品id
        long itemId = IDUtils.genItemId();

        // 补全TbItem对象的属性
        item.setId(itemId);
        item.setStatus((byte) 1);   // 1-正常，2-下架，3-删除
        item.setCreated(new Date());
        item.setUpdated(new Date());

        // 向商品表插入数据
        itemMapper.insert(item);

        // 创建一个商品描述表TbItemDesc对应的pojo对象
        TbItemDesc itemDesc = new TbItemDesc();

        // 补全TbItemDesc的属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        // 向商品描述表插入数据
        itemDescMapper.insert(itemDesc);

        // 返回成功
        return E3Result.ok();
    }
}
