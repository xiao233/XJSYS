package com.java.constants;
/**
 * 返回代码集消息常量类
 * @author xjl
 * 2018-09-13 10:46:47
 */
public class CodeMsgConstants {

	/**
	 * 注册登录名字为空
	 */
	public static final String L_R_NAME_IS_EMPTY = "LR0001";
	public static final String L_R_NAME_IS_EMPTY_MSG = "用户名为空！";
	/**
	 * 注册登录密码为空
	 */
	public static final String L_R_PWD_ISEMPTY = "LR0002";
	public static final String L_R_PWD_ISEMPTY_MSG = "密码为空！";
	/**
	 * 登录成功
	 */
	public static final String L_LOGIN_SUCC = "L0000";
	public static final String L_LOGIN_SUCC_MSG = "登录成功！";
	/**
	 * 登录失败
	 */
	public static final String L_LOGIN_FAIL = "L0001";
	public static final String L_LOGIN_FAIL_MSG = "登录失败，用户名或密码不正确！";
	/**
	 * 注册成功
	 */
	public static final String R_REGISTER_SUCC = "R0000";
	public static final String R_REGISTER_SUCC_MSG = "注册成功,请返回登录！";
	/**
	 * 注册两次密码不一致
	 */
	public static final String R_REGISTER_FAIL_PAW_DIFFERENT = "R0001";
	public static final String R_REGISTER_FAIL_PAW_DIFFERENT_MSG = "注册两次密码不一致！";
	
	
	/**
	 * 操作失败
	 */
	public static final String OPERATION_FAIL="0001";
	public static final String OPERATION_FAIL_MSG="操作失败！";

	/**
	 * 图片验证码为空
	 */
	public static final String CODE_IS_EMPTY = "0002";
	public static final String CODE_IS_EMPTY_MSG = "图片验证码为空！";
	/**
	 * 图片验证码为不正确
	 */
	public static final String CODE_IS_ERROR = "0003";
	public static final String CODE_IS_ERROR_MSG = " 图片验证码为不正确！";
	/**
	 * 生成图片验证码成功
	 */
	public static final String CODE_BORN_SUCC = "0004";
	public static final String CODE_BORN_SUCC_MSG = " 生成图片验证码成功！";
	/**
	 * 生成图片验证码失败
	 */
	public static final String CODE_BORN_FAIL = "0005";
	public static final String CODE_BORN_FAIL_MSG = " 生成图片验证码失败！";
}
