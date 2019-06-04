package com.loto.e3mall.portal.controller;

import com.loto.e3mall.content.service.ContentService;
import com.loto.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author：蓝田_Loto
 * Date：2019-04-11 20:47
 * PageName：IndexController.java
 * Function：前台页面控制层
 */

@Controller
public class IndexController {
    // 从resource.properties中取出
    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @Autowired
    private ContentService contentService;

    // 前台主页
    @RequestMapping("/index")
    public String showIndex(Model model) {
        // 查询内容列表
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);

        // 把结果传递给页面
        model.addAttribute("ad1List", ad1List);
        return "index";
    }
}
