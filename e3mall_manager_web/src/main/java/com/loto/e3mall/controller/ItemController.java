package com.loto.e3mall.controller;

// 商品管理Controller

import com.loto.e3mall.common.pojo.EasyUIDataGridResult;
import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.pojo.TbItem;
import com.loto.e3mall.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ItemController {
    // @Autowired
	@Resource
	private ItemService itemService;

	// 根据商品id查询商品信息  536563
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
	}

	// 查询所有商品列表，进行分页处理
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
	}

	// 商品添加功能
	@RequestMapping(value="/item/save", method= RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item, String desc) {
        return itemService.addItem(item, desc);
	}
}
