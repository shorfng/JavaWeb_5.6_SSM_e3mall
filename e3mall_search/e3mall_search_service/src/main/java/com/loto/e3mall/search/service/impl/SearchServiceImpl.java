package com.loto.e3mall.search.service.impl;

import com.loto.e3mall.common.pojo.SearchResult;
import com.loto.e3mall.search.dao.SearchDao;
import com.loto.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：蓝田_Loto
 * Date：2019-06-12 23:40
 * PageName：SearchServiceImpl.java
 * Function：商品搜索
 */

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    // 查询商品列表
    @Override
    public SearchResult search(String keyword, int page, int rows) throws Exception {
        // 创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();

        // 设置查询条件
        query.setQuery(keyword);

        // 设置分页条件
        if (page <=0 ) page =1;
        query.setStart((page - 1) * rows);
        query.setRows(rows);

        // 设置默认搜索域
        query.set("df", "item_title");

        // 开启高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        // 调用dao执行查询（根据查询条件查询索引库）
        SearchResult searchResult = searchDao.search(query);

        // 计算总页数
        long recordCount = searchResult.getRecordCount();
        int totalPage = (int) (recordCount / rows);
        if (recordCount % rows > 0)
            totalPage ++;

        // 添加到返回结果
        searchResult.setTotalPages(totalPage);

        // 返回结果
        return searchResult;
    }
}
