package com.loto.e3mall.content.service;

import com.loto.e3mall.common.pojo.EasyUITreeNode;
import com.loto.e3mall.common.utils.E3Result;

import java.util.List;

/**
 * Author：蓝田_Loto
 * Date：2019-04-29 13:19
 * PageName：ContentCategoryService.java
 * Function：内容分类服务
 */

public interface ContentCategoryService {
    // 展示内容分类列表
    List<EasyUITreeNode> getContentCatList(long parentId);

    // 添加内容分类节点
    E3Result addContentCategory(long parentId, String name);
}
