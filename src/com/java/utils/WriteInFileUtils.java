package com.java.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.common.Logger;

/**
 * д���ļ�
 * @author xjl
 * 2018-12-25 14:39:12
 */
public class WriteInFileUtils {
	
	private static Logger log = Logger.getLogger(WriteInFileUtils.class);

	/**
	 * д�ļ��������Ĭ������·��
	 * 2018-12-25 14:40:57
	 * @param queryResult
	 * @param string
	 * @param response
	 */
	public static <T> void writeInFile(List<T> queryResult, String file,
			HttpServletResponse response) {
		
		 response.setCharacterEncoding("utf-8");
         //������Ӧ���ݵ�����
         response.setContentType("text/plain;charset=utf-8");
         //�����ļ������ƺ͸�ʽ
         response.setHeader(
                 "Content-Disposition",
                 "attachment; filename="+file);//ͨ����׺�������ز�ͬ���ļ���ʽ
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
			log.error("�����ļ�ʧ�ܣ� "+e.getMessage());
		}finally {
			try {
				soos.close();
				bos.close();
			} catch (IOException e) {
				log.error("�ر���������� "+e.getMessage());
			}
		}
		
	}
}
