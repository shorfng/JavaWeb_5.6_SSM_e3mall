package com.loto.e3mall.service.impl;

// 商品管理Service

import java.util.List;

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
}
