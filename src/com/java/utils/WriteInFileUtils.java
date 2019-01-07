package com.java.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.Workbook;
import jxl.common.Logger;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
	 * @param tableName
	 */
	public static  void writeInFile(List<Map<String,Object>> queryResult, String file,
			HttpServletResponse response,String tableName) {
		
		 response.setCharacterEncoding("utf-8");
         //������Ӧ���ݵ�����
         response.setContentType("multipart/form-data;charset=utf-8");
         //�����ļ������ƺ͸�ʽ
         response.setHeader(
                 "Content-Disposition",
                 "attachment; filename="+file);//ͨ����׺�������ز�ͬ���ļ���ʽ
		if(file.endsWith(".txt")) {
			writeInTxtFile(queryResult, file, response);
		}else if(file.endsWith(".xlsx")){
			writeInXlsxFile(queryResult, file, response,tableName);
		}
		
	}
	
	/**
	 * ���뵽Excel�ļ�
	 * 2018-12-29 17:18:30
	 * @param queryResult
	 * @param file
	 * @param response
	 * @param tableName
	 */
	private static void writeInXlsxFile(List<Map<String, Object>> queryResult, String file,
			HttpServletResponse response, String tableName) {
		try {
			//����������
			WritableWorkbook wb = Workbook.createWorkbook(response.getOutputStream());
			//�������
			WritableSheet ws = wb.createSheet(tableName, 1);//�����ƣ���λ��
			
			//��������
			WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			//����������ɫ
			font.setColour(Colour.RED);
			
			//������ʽ
			WritableCellFormat format = new WritableCellFormat(font);
			//���ñ�����ɫ
			format.setBackground(Colour.IVORY);
			
			int row = 1;//����
			boolean showFiled = true;
			for (Map<String, Object> map : queryResult) {
				
				//������Ԫ��
				Label label = null;
				int column = 0;//��
				//���ñ��ÿ�е�����
				if(showFiled) {
					for (String key : map.keySet()) {
						label = new Label(column, 0, key,format);//�С��С�ֵ����ʽ
						//����Ԫ����ӵ����
						ws.addCell(label);
						column++;
					}
					showFiled=false;
				}
				
				column = 0;//��
				CellView cellView = new CellView();  
			    cellView.setAutosize(true); //�����Զ���С
			    
			    //��������
				for(String key : map.keySet()) {
					ws.setColumnView(column, cellView);//���������Զ������п�  
					
					String value = null;
					Object obj = map.get(key);
					if( obj instanceof Timestamp) {
						value =  obj.toString().substring(0, obj.toString().indexOf("."));
					}else {
						value = obj.toString();
					}
					label = new Label(column, row, value);//�С��С�ֵ
					column++;
					//����Ԫ����ӵ����
					ws.addCell(label);
				}
				row++;
			}
			//д�����ݵ�������
			wb.write();
			wb.close();
			log.info("-------->�����ļ�"+file+"�ɹ���");
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (RowsExceededException e) {
			log.error(e.getMessage());
		} catch (WriteException e) {
			log.error(e.getMessage());
		}
		
	}

	/**
	 * ���뵽.txt�ļ�
	 * 2018-12-29 17:18:03
	 * @param queryResult
	 * @param file
	 * @param response
	 */
	public static void writeInTxtFile(List<Map<String,Object>> queryResult, String file,
			HttpServletResponse response) {
		ServletOutputStream soos = null;
		BufferedOutputStream  bos = null;
		
		try {
			soos = response.getOutputStream();
			bos = new BufferedOutputStream(soos);
			
			boolean showFiled = true;
			for (Map<String, Object> map : queryResult) {
				
				String header = "";
				
				//���õ�����ֶ���
				if(showFiled) {
					for (String key : map.keySet()) {
						header+=key+"    ";
					}
					header+="\r\n\r\n";
					bos.write(header.getBytes("UTF-8"));
					showFiled=false;
				}
				
				String line = "";
				//�����ֶζ�Ӧ��ֵ
				for (String key : map.keySet()) {
					String value = null;
					Object obj = map.get(key);
					if( obj instanceof Timestamp) {
						value =  obj.toString().substring(0, obj.toString().indexOf("."));
					}else {
						value = obj.toString();
					}
					
					if(value==null||value.trim().length()==0) {
						line+="-    ";
					}else {
						line+=value+"    ";
					}
				}
				line+="\r\n";
				bos.write(line.getBytes("UTF-8"));
			}
			bos.flush();
			log.info("-------->�����ļ�"+file+"�ɹ���");
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
