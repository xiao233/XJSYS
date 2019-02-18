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
	/**
	 * 从redis获取图片验证码异常
	 */
	public static final String CODE_REDIS_ERROR = "0006";
	public static final String CODE_REDIS_ERROR_MSG = "从redis获取图片验证码异常！";
	
	/**
	 * 查询成功，但没有记录
	 */
	public static final String QUERY_SUCC_NO_RECORD = "Q0001";
	public static final String QUERY_SUCC_NO_RECORD_MSG = "查询成功，但没有记录！";
	
	/**
	 * 查询成功，有记录
	 */
	public static final String QUERY_SUCC_HAS_RECORD = "Q0000";
	public static final String QUERY_SUCC_HAS_RECORD_MSG = "查询成功，且有记录！";
	
	
	/**
	 * 查询失败
	 */
	public static final String QUERY__FAILD= "Q0002";
	public static final String QUERY__FAILD_MSG = "查询失败！";
	
	/**
	 * 更新成功
	 */
	public static final String UPDATE_SUCCESS = "U0000";
	public static final String UPDATE_SUCCESS_MSG = "更新成功！";
	
	/**
	 * 更新失败
	 */
	public static final String UPDATE_FAILD = "U0001";
	public static final String UPDATE_FAILD_MSG = "更新失败！";
	
	/**
	 * 删除成功
	 */
	public static final String DELETE_SUCCESS = "D0000";
	public static final String DELETE_SUCCESS_MSG = "删除成功！";
	
	/**
	 * 删除失败
	 */
	public static final String DELETE_FAILD = "D0001";
	public static final String DELETE_FAILD_MSG = "删除失败！";
	
	/**
	 * 添加成功
	 */
	public static final String CREATE_SUCCESS = "C0000";
	public static final String CREATE_SUCCESS_MSG = "添加成功！";
	
	/**
	 * 添加失败
	 */
	public static final String CREATE_FAILD = "C0001";
	public static final String CREATE_FAILD_MSG = "添加失败！";
	
	
}
