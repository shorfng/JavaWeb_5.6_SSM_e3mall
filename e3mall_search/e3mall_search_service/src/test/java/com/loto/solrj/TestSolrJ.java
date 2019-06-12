package com.loto.solrj;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Author：蓝田_Loto
 * Date：2019-06-12 18:46
 * PageName：TestSolrJ.java
 * Function：测试（使用SolrJ实现索引库的增删改查操作）
 */


public class TestSolrJ {
    // 添加文档到索引库
    @Test
    public void addDocument() throws Exception {
        // 创建一个SolrServer对象，创建一个连接。参数solr服务的url
        SolrServer solrServer = new HttpSolrServer("http://192.168.31.130:8983/solr/core1");

        // 创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();

        // 向文档对象中添加域（文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义）
        document.addField("id", "doc01");
        document.addField("item_title", "测试商品01");
        document.addField("item_price", 1000);

        // 把文档写入索引库
        solrServer.add(document);

        // 提交
        solrServer.commit();
    }

    // 从索引库中查询文档
    @Test
    public void queryIndex() throws Exception {
        // 创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.31.130:8983/solr/core1");

        // 创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();

        // 设置查询条件
        //query.setQuery("*:*");
        query.set("q", "*:*");

        // 执行查询（QueryResponse对象）
        QueryResponse queryResponse = solrServer.query(query);

        // 取文档列表（取查询结果的总记录数）
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());

        // 遍历文档列表，从取域的内容（默认取10条记录）
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
            System.out.println("==================================================================================");
            System.out.println();
        }
    }

    // 从索引库中查询文档（复杂条件，显示高亮）
    @Test
    public void queryIndexFuza() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.31.130:8983/solr/core1");

        // 创建一个查询对象
        SolrQuery query = new SolrQuery();

        // 查询条件
        query.setQuery("手机");
        query.setStart(0);
        query.setRows(20);
        query.set("df", "item_title");
        query.setHighlight(true);               // 开启高亮显示
        query.addHighlightField("item_title");  // 高亮显示的域
        query.setHighlightSimplePre("<em>");    // 高亮显示的前缀
        query.setHighlightSimplePost("</em>");  // 高亮显示的后缀

        // 执行查询
        QueryResponse queryResponse = solrServer.query(query);

        // 取文档列表，取查询结果的总记录数
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());

        // 遍历文档列表，从取域的内容
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));

            // 取高亮显示
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";

            // 判断list，看是否有高亮，有高亮取第一个数据的值，没有高亮取原文档的item_title的值
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }

            System.out.println(title);
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
            System.out.println("==================================================================================");
            System.out.println();
        }
    }

    // 从索引库中删除文档
    @Test
    public void deleteDocument() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.31.130:8983/solr/core1");

        // 删除文档
        //solrServer.deleteById("doc01");     // 根据文档id删除
        solrServer.deleteByQuery("id:doc01"); // 通过查询语法删除

        // 提交
        solrServer.commit();
    }

}
