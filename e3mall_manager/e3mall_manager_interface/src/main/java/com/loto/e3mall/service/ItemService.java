package com.loto.e3mall.service;

import com.loto.e3mall.common.pojo.EasyUIDataGridResult;
import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.pojo.TbItem;

public interface ItemService {

	// 根据商品id查询商品信息
	TbItem getItemById(long itemId);

	// 查询所有商品列表，进行分页处理
	EasyUIDataGridResult getItemList(int page, int rows);

	// 商品添加功能
	E3Result addItem(TbItem item, String desc);
}
