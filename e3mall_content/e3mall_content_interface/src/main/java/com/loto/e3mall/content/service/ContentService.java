package com.loto.e3mall.content.service;

import com.loto.e3mall.common.utils.E3Result;
import com.loto.e3mall.pojo.TbContent;

/**
 * Author：蓝田_Loto
 * Date：2019-05-31 18:47
 * PageName：ContentService.java
 * Function：内容管理
 */

public interface ContentService {
    // 新增内容
    E3Result addContent(TbContent content);
}
