package com.sxt.sys.request;

import lombok.Data;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 17:11
 */
@Data
public class AppTaskRequest {
    //用户openId
    private String openId;
    //是否参与
    private Boolean checked;
    //是否报名
    private Boolean signUp;
    //是否结束
    private Boolean end;
}
