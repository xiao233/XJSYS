package com.java.entites.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于返回到前端的结果
 * @author xjl
 * 2018-09-13 10:21:55
 */
public class CodeMessageResult<T> {
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 结果集
	 */
	private List<T> result = new ArrayList<T>();
	
	private Page page = new Page();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
