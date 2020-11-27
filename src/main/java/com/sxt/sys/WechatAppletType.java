package com.sxt.sys;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 15:39
 */
public enum WechatAppletType {
    VOLUNTEER("志愿者小程序");
    private String name;

    private WechatAppletType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
