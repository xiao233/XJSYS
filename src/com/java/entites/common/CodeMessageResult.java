package com.java.entites.common;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ڷ��ص�ǰ�˵Ľ��
 * @author xjl
 * 2018-09-13 10:21:55
 */
public class CodeMessageResult<T> {
	/**
	 * ����
	 */
	private String code;
	/**
	 * ��Ϣ
	 */
	private String msg;
	/**
	 * �����
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
