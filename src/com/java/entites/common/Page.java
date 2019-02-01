package com.java.entites.common;
/**
 * ��ҳ��Ϣ
 * @author xjl
 * 2019-01-28 10:40:46
 */
public class Page {
	/**
	 * ÿҳ��С
	 */
	private int pageSize=10;
	
	/**
	 * ��ǰҳ,��1��ʼ
	 */
	private int pageCurr;
	
	/**
	 * �ܵ�ҳ��
	 */
	private int pageTotal;
	
	
	/**
	 * �ܵ���������
	 */
	private int dataTotal=-1;
	
	/**
	 * ���ݿ��ѯ��ʼ
	 */
	private int dataStart;
	
	public Page() {
		
	}
	

	public Page(int pageSize, int pageCurr) {
		super();
		if(pageSize<=0) {
			pageSize = 10;
		}
		if(pageCurr<=0) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
		this.pageCurr = pageCurr;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		if(pageSize<=0) {
			pageSize = 10;
		}
		
		this.pageSize = pageSize;
	}


	public int getPageCurr() {
		if(pageCurr<=0) {
			pageCurr = 1;
		}
		return pageCurr;
	}


	public void setPageCurr(int pageCurr) {
		if(pageCurr<=0) {
			pageSize = 1;
		}
		this.pageCurr = pageCurr;
	}


	public int getPageTotal() {
		if(this.dataTotal>0) {
			pageTotal = (int) Math.ceil(dataTotal/pageSize);
		}
		return pageTotal;
	}


	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}


	public int getDataTotal() {
		return dataTotal;
	}


	public void setDataTotal(int dataTotal) {
		this.dataTotal = dataTotal;
	}


	public int getDataStart() {
		dataStart = (getPageCurr()-1)*getPageSize();
		return dataStart;
	}


	public void setDataStart(int dataStart) {
		this.dataStart = dataStart;
	}

}
