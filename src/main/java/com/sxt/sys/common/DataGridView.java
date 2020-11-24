package com.sxt.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Json数据实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataGridView {
    private Integer code=0;
    private String msg="";
    private Long count=0L;
    private Object data;

    public DataGridView(Object data) {
        this.data = data;
    }

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
