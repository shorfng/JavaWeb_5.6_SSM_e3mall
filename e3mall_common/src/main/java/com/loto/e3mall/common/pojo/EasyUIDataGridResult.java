package com.loto.e3mall.common.pojo;

// 响应的json数据格式的pojo

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult implements Serializable {

    private long total;  // 总数
    private List rows;   // 行

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
