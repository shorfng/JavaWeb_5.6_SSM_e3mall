package com.loto.e3mall.search.service;

import com.loto.e3mall.common.pojo.SearchResult;

/**
 * Author：蓝田_Loto
 * Date：2019-06-12 23:41
 * PageName：SearchService.java
 * Function：商品搜索
 */

public interface SearchService {
    // 查询商品列表
    SearchResult search(String keyword, int page, int rows)  throws Exception;
}
