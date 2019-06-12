package com.loto.e3mall.search.service;

import com.loto.e3mall.common.utils.E3Result;

/**
 * Author：蓝田_Loto
 * Date：2019-06-12 21:28
 * PageName：SearchItemService.java
 * Function：
 */

public interface SearchItemService {
    // 导入商品数据到索引库
    E3Result importAllItems();
}
