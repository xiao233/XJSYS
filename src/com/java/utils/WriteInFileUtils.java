package com.java.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.common.Logger;

/**
 * 写入文件
 * @author xjl
 * 2018-12-25 14:39:12
 */
public class WriteInFileUtils {
	
	private static Logger log = Logger.getLogger(WriteInFileUtils.class);

	/**
	 * 写文件到浏览器默认下载路劲
	 * 2018-12-25 14:40:57
	 * @param queryResult
	 * @param string
	 * @param response
	 */
	public static <T> void writeInFile(List<T> queryResult, String file,
			HttpServletResponse response) {
		
		 response.setCharacterEncoding("utf-8");
         //设置响应内容的类型
         response.setContentType("text/plain;charset=utf-8");
         //设置文件的名称和格式
         response.setHeader(
                 "Content-Disposition",
                 "attachment; filename="+file);//通过后缀可以下载不同的文件格式
		ServletOutputStream soos = null;
		BufferedOutputStream  bos = null;
		
		try {
			soos = response.getOutputStream();
			bos = new BufferedOutputStream(soos);
			for (T t : queryResult) {
				
			}
			bos.write("qaq".getBytes());
			bos.flush();
		} catch (IOException e) {
			log.error("导出文件失败： "+e.getMessage());
		}finally {
			try {
				soos.close();
				bos.close();
			} catch (IOException e) {
				log.error("关闭流对象出错： "+e.getMessage());
			}
		}
		
	}
}
