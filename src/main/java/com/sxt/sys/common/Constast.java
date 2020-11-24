package com.sxt.sys.common;

public class Constast {
	/**
	 * 状态码
	 */
	public static final Integer OK = 200;
	public static final Integer ERROR = -1;
	/**
	 * 菜单权限类型
	 */
	public static final String TYPE_MENU = "menu";
	public static final String TYPE_PERMISSION = "permission";
	/**
	 * 是否可用状态
	 */
	public static final Object AVAILABLE_TRUE = 1;
	public static final Object AVAILABLE_FALSE = 0;
	/**
	 * 超级管理员与普通用户
	 */
	public static final Integer USER_TYPE_SUPER = 0;
	public static final Integer USER_TYPE_NORMAL = 1;
	/**
	 * 是否展开
	 */
	public static final Integer OPEN_TRUE = 1;
	public static final Integer OPEN_FLASE = 0;
	/**
	 * 默认密码
	 */
	public static final String USER_DEFAULT_PWD = "123456";
	/**
	 * 请假单状态
	 */
	public static final String STATE_LEAVEBILL_ZORO = "0";// 未提交
	public static final String STATE_LEAVEBILL_ONE = "1";// 审批中
	public static final String STATE_LEAVEBILL_TOW = "2";// 审批完成
	public static final String STATE_LEAVEBILL_THREE = "3";// 放弃

	/**
	 * 默认头像
	 */
	public static final String IMAGES_DEFAULTIMG_JPG = "images/defaultimg.jpg";
}
