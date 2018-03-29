package com.loto.e3mall.service.impl;

// 商品管理Service

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loto.e3mall.common.pojo.EasyUIDataGridResult;
import com.loto.e3mall.mapper.TbItemMapper;
import com.loto.e3mall.pojo.TbItem;
import com.loto.e3mall.pojo.TbItemExample;
import com.loto.e3mall.pojo.TbItemExample.Criteria;
import com.loto.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

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
}
