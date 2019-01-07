package com.java.utils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 将不同数据格式转成相应对象
 * @author xjl
 * 2018-12-25 13:44:07
 */
public class ConvertToObjectUtils {
	
	private static Logger log = Logger.getLogger(ConvertToObjectUtils.class);

	/**
	 * 将从数据库查询的数据转成相应对象
	 * 2018-12-25 13:44:53
	 * @param queryList 数据库查询内容
	 * @param tableClass 对象类名
	 * @return
	 */
	public static  List<Object> mapToObject(List<Map<String, Object>> queryList, Class<?> tableClass) {
		List<Object> result = new ArrayList<Object>();
		
		Field []fields = tableClass.getDeclaredFields();
		
		
		for (Map<String, Object> map : queryList) {
			try {
				Object temp = tableClass.newInstance();
				Set<Entry<String, Object>> entrys = map.entrySet();
				for (Entry<String, Object> entry : entrys) {
					String fieldName = StringUtils.changeName(entry.getKey());
					for (Field field : fields) {
						if(field.getName().equals(fieldName)) {
							field.setAccessible(true);
							String type = field.getGenericType().getTypeName();
							String value =  "";
							if(entry.getValue() instanceof Timestamp) {
								Timestamp time = (Timestamp) entry.getValue();
								value = time.toString().substring(0, time.toString().indexOf("."));
							}else {
								value += entry.getValue();
							}
							if(type.indexOf("String")>=0) {
								field.set(temp, value);
							}else if(type.indexOf("Integer")>=0) {
								field.set(temp, Integer.parseInt(value));
							}else if(type.indexOf("Double")>=0) {
								field.set(temp, Double.parseDouble(value));
							}else if(type.indexOf("Timestamp")>=0) {
								field.set(temp,Timestamp.valueOf(value));
							}
							break;
						}
					}
				}
				result.add(temp);
			} catch (InstantiationException | IllegalAccessException e) {
				log.error(e.getMessage());
			}
		}
		return result;
	}

}
