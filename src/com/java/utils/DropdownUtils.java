package com.java.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.ViewDropCfgDao;
import com.java.entites.ViewDropCfg;
/**
 * ��ȡ��������Ϣ
 * @author xjl
 * 2018-12-24 11:23:47
 */
public class DropdownUtils {
	
	private static Logger log = Logger.getLogger(DropdownUtils.class);
	
	/**
	 * ��ȡ��������Ϣ
	 * 2018-12-24 09:19:34
	 * @param dropName
	 * @return
	 */
	public static List<ViewDropCfg> getDropCfg(String dropdownName){
		List<ViewDropCfg> list = new ArrayList<ViewDropCfg>();
		if(StringUtils.isEmpty(dropdownName)) {
			log.error("�����������Ϊ�գ�");
			return list;
		}
		try {
			ViewDropCfgDao viewDropCfgDao = (ViewDropCfgDao) GetBeanUtils.getBean("viewDropCfgDao");
			list = viewDropCfgDao.getDropCfg(dropdownName);
			return list;
		} catch (Exception e) {
			log.error("��ȡ��������Ϣ�쳣��"+e.getMessage());
			return list;
		}
	}
}
