package com.sxt.sys.Request;

import lombok.Data;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 15:01
 */
@Data
public class VolunteerQueryRequest {
    private String name;
    private String idCard;
    private Boolean isChecked;
    private int pageIndex=0;
    private int page=10;

}
