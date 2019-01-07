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
	 * @param tableName
	 */
	public static  void writeInFile(List<Map<String,Object>> queryResult, String file,
			HttpServletResponse response,String tableName) {
		
		 response.setCharacterEncoding("utf-8");
         //设置响应内容的类型
         response.setContentType("multipart/form-data;charset=utf-8");
         //设置文件的名称和格式
         response.setHeader(
                 "Content-Disposition",
                 "attachment; filename="+file);//通过后缀可以下载不同的文件格式
		if(file.endsWith(".txt")) {
			writeInTxtFile(queryResult, file, response);
		}else if(file.endsWith(".xlsx")){
			writeInXlsxFile(queryResult, file, response,tableName);
		}
		
	}
	
	/**
	 * 导入到Excel文件
	 * 2018-12-29 17:18:30
	 * @param queryResult
	 * @param file
	 * @param response
	 * @param tableName
	 */
	private static void writeInXlsxFile(List<Map<String, Object>> queryResult, String file,
			HttpServletResponse response, String tableName) {
		try {
			//创建工作簿
			WritableWorkbook wb = Workbook.createWorkbook(response.getOutputStream());
			//创建表格
			WritableSheet ws = wb.createSheet(tableName, 1);//表名称，表位置
			
			//设置字体
			WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			//设置字体颜色
			font.setColour(Colour.RED);
			
			//设置样式
			WritableCellFormat format = new WritableCellFormat(font);
			//设置背景颜色
			format.setBackground(Colour.IVORY);
			
			int row = 1;//行数
			boolean showFiled = true;
			for (Map<String, Object> map : queryResult) {
				
				//创建单元格
				Label label = null;
				int column = 0;//列
				//设置表格每列的名称
				if(showFiled) {
					for (String key : map.keySet()) {
						label = new Label(column, 0, key,format);//列、行、值、样式
						//将单元格添加到表格
						ws.addCell(label);
						column++;
					}
					showFiled=false;
				}
				
				column = 0;//列
				CellView cellView = new CellView();  
			    cellView.setAutosize(true); //设置自动大小
			    
			    //设置内容
				for(String key : map.keySet()) {
					ws.setColumnView(column, cellView);//根据内容自动设置列宽  
					
					String value = null;
					Object obj = map.get(key);
					if( obj instanceof Timestamp) {
						value =  obj.toString().substring(0, obj.toString().indexOf("."));
					}else {
						value = obj.toString();
					}
					label = new Label(column, row, value);//列、行、值
					column++;
					//将单元格添加到表格
					ws.addCell(label);
				}
				row++;
			}
			//写入数据到工作簿
			wb.write();
			wb.close();
			log.info("-------->导入文件"+file+"成功！");
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (RowsExceededException e) {
			log.error(e.getMessage());
		} catch (WriteException e) {
			log.error(e.getMessage());
		}
		
	}

	/**
	 * 导入到.txt文件
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
				
				//设置导入的字段名
				if(showFiled) {
					for (String key : map.keySet()) {
						header+=key+"    ";
					}
					header+="\r\n\r\n";
					bos.write(header.getBytes("UTF-8"));
					showFiled=false;
				}
				
				String line = "";
				//导入字段对应的值
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
			log.info("-------->导入文件"+file+"成功！");
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
