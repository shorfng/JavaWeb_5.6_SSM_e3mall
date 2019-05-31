package com.loto.e3mall.controller;

import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.content.service.ContentService;
import com.loto.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author：蓝田_Loto
 * Date：2019-05-31 18:53
 * PageName：ContentController.java
 * Function：内容管理
 */

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    // 新增内容
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content) {
        // 调用服务把内容数据保存到数据库
        E3Result e3Result = contentService.addContent(content);
        return e3Result;
    }
}
