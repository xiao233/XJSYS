package com.java.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.utils.StringUtils;
/**
 * ���˵�����ȫ������Դ����վʽ������
 * @author xjl
 * 2018-09-17 14:40:42
 */
public class RefererFilter implements Filter {

	private String rootURL;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		/**
		 * �ж��û��Ƿ��¼
		 */
		Object obj = session.getAttribute("userInf");
		if(obj!=null) {
			fc.doFilter(req, res);
			return;
		}
		String referer = request.getHeader("Referer");
		String current = request.getServletPath();
		if(rootURL==null) {
			String temp = request.getRequestURL().toString();
			String contentPath = request.getContextPath();
			rootURL = temp.substring(0, temp.indexOf(contentPath)+contentPath.length());
		}
		if(!isSafe(referer,current)) {
			response.sendRedirect("index");
			return;
		}
		fc.doFilter(req, res);
	}

	/**
	 * �жϵ�ǰ������Դ�Ƿ�ɿ�
	 * 2018-09-17 14:40:02
	 * @param referer
	 * @param current
	 * @return
	 */
	private boolean isSafe(String referer, String current) {
		if("/index".equals(current)) {
			return true;
		}
		if(StringUtils.isEmpty(referer)) {
			return false;
		}
		if(referer.indexOf(rootURL)>=0) {
			return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
