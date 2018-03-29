package com.loto.e3mall.service;

import com.loto.e3mall.common.pojo.EasyUITreeNode;
import java.util.List;


public interface ItemCatService {

	// 根据parentId查询节点列表
	List<EasyUITreeNode> getItemCatlist(long parentId);
}
