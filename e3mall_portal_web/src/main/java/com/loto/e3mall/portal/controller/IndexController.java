package com.loto.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author：蓝田_Loto
 * Date：2019-04-11 20:47
 * PageName：IndexController.java
 * Function：前台页面控制层
 */

@Controller
public class IndexController {
    // 前台主页
    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

}
