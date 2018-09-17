package com.java.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.java.utils.StringUtils;

/**
 * xss¹¥»÷¹ýÂË
 * @author xjl
 * 2018-09-17 11:35:06
 */
public class XSSFilter implements Filter {
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		/*req.setCharacterEncoding("UTF-8");
		Enumeration<?> enums = req.getParameterNames();
		
		while(enums.hasMoreElements()) {
			String name = (String) enums.nextElement();
			if(!StringUtils.isEmpty(name)) {
				if(xssBlack(name)) {
					return;
				}
			}
			String values[]=req.getParameterValues(name);
			if(values!=null) {
				for (String value : values) {
					if(!StringUtils.isEmpty(value)) {
						if(xssBlack(value)) {
							return;
						}
					}
				}
			}
		}*/
		
		fc.doFilter(req, res);
		
	}

	private boolean xssBlack(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
