package com.loto.e3mall.controller;

// 商品分类管理
import java.util.List;

import com.loto.e3mall.common.pojo.EasyUITreeNode;
import com.loto.e3mall.service.ItemCatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class ItemCatController {

	// @Autowired
	@Resource
	private ItemCatService itemCatService;

	// 根据parentId查询节点列表
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
		// 调用服务查询节点列表
		List<EasyUITreeNode> list = itemCatService.getItemCatlist(parentId);
		return list;
	}
}
