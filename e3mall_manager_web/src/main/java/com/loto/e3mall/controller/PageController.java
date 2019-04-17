package com.loto.e3mall.controller;

// 页面跳转Controller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	// 展示后台首页
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	// 点击后台功能跳转到相关页面
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
