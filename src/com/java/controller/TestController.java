package com.java.controller;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AccessFieldTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.UserInfService;

/**
 * 专门用于测试的路劲，无需登录
 * @author xjl
 * 2019-03-22 16:20:11
 */
@Controller
@RequestMapping(value="test/route1")
public class TestController {
	
	@Autowired
	private UserInfService userInfService;
	
	private int ca = 0;
	
	private volatile int vb = 0;
	
	private volatile int vc = 0;
	
	private AtomicInteger ai = new AtomicInteger(0);
	
	/**
	 * 测试高并发的问题
	 * 2019-03-22 16:26:56
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/hugeReq")
	public Map<String,String> testHugeRequest(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer content = new StringBuffer("{");
		
		content.append("userIndService:"+userInfService);
		content.append(",");
		content.append("url:{");
		content.append("contentPath:"+request.getContextPath());
		content.append(",");
		content.append("servletPath:"+request.getServletPath());
		
		try {
			URL url = new URL(request.getRequestURL().toString());
			Inet4Address inet4 = (Inet4Address) Inet4Address.getByName(url.getHost());
			
			String ip ="";
			byte ips[] = inet4.getAddress();
			for (byte b : ips) {
				ip+=b+".";
			}
			ip=ip.substring(0, ip.length()-1);
			
			content.append(",");
			content.append("ip:"+ip);
		} catch (MalformedURLException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content.append("}");
		
		Thread th = Thread.currentThread();
		content.append(",");
		content.append("thread:"+th.getName());
		
		content.append(",");
		content.append("this:"+this.hashCode());
		
		content.append(",");
		content.append("普通共享变量:"+(++ca));
		
		content.append(",");
		content.append("volatile共享变量vb:"+(++vb));
		
		vc=vc+1;
		content.append(",");
		content.append("volatile共享变量vc:"+vc);
		
		content.append(",");
		content.append("AtomicInteger共享变量:"+ai.incrementAndGet());
		
		content.append("}");

		map.put("code", "test-succ");
		map.put("msg", "测试成功！");
		map.put("content", content.toString());
		return map;
	}
}
