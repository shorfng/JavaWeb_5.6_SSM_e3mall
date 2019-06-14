package com.loto.e3mall.search.mapper;

import com.loto.e3mall.common.pojo.SearchItem;

import java.util.List;

/**
 * Author：蓝田_Loto
 * Date：2019-06-12 18:46
 * PageName：ItemMapper.java
 * Function：
 */

public interface ItemMapper {
    List<SearchItem> getItemList();

    // 根据商品id查询
    SearchItem getItemById(long itemId);
}
