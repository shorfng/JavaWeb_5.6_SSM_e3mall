package com.loto.solrj;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;


/**
 * Author：蓝田_Loto
 * Date：2019-06-13 21:58
 * PageName：TestSolrCloud.java
 * Function：Solr集群测试（测试未成功）
 */

// TODO:在服务器使用solr7搭建集群，启动用jetty，测试未通过，待解决
// 异常：org.apache.solr.common.SolrException: Could not find collection : collection1_shard1_replica_n1
public class TestSolrCloud {
    // 创建一个和solr集群的连接，参数为zookeeper地址，以逗号分隔
    private static final String ZK_HOST = "192.168.31.130:21811,192.168.31.130:21812,192.168.31.130:21813";

    //@Test
    //public void testAddDocument() throws Exception {
    //    // 创建一个集群的连接（zkHost：zookeeper的地址列表）
    //    CloudSolrServer solrServer = new CloudSolrServer(ZK_HOST);
    //
    //    // 设置一个defaultCollection属性
    //    solrServer.setDefaultCollection("collection1_shard1_replica_n1");
    //
    //    // 创建一个文档对象
    //    SolrInputDocument document = new SolrInputDocument();
    //
    //    // 向文档中添加域
    //    document.setField("id", "solrcloud01");
    //    document.setField("item_title", "测试商品01");
    //    document.setField("item_price", 123);
    //
    //    // 把文件写入索引库
    //    solrServer.add(document);
    //
    //    // 提交
    //    solrServer.commit();
    //}

    //@Test
    //public void testQueryDocument() throws Exception {
    //    //创建一个CloudSolrServer对象
    //    CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.25.163:2181,192.168.25.163:2182,192.168.25.163:2183");
    //    //设置默认的Collection
    //    cloudSolrServer.setDefaultCollection("collection2");
    //    //创建一个查询对象
    //    SolrQuery query = new SolrQuery();
    //    //设置查询条件
    //    query.setQuery("*:*");
    //    //执行查询
    //    QueryResponse queryResponse = cloudSolrServer.query(query);
    //    //取查询结果
    //    SolrDocumentList solrDocumentList = queryResponse.getResults();
    //    System.out.println("总记录数：" + solrDocumentList.getNumFound());
    //    //打印
    //    for (SolrDocument solrDocument : solrDocumentList) {
    //        System.out.println(solrDocument.get("id"));
    //        System.out.println(solrDocument.get("title"));
    //        System.out.println(solrDocument.get("item_title"));
    //        System.out.println(solrDocument.get("item_price"));
    //    }
    //}
}
