package com.loto.e3mall.controller;

import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author：蓝田_Loto
 * Date：2019-06-12 21:46
 * PageName：.java
 * Function：导入商品数据到索引库
 */

@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;

    // 导入商品数据到索引库
    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importItemList() {
        E3Result e3Result = searchItemService.importAllItems();
        return e3Result;

    }
}
