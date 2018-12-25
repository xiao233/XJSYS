package com.java.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entites.SearchParamObject;
import com.java.entites.ViewDropCfg;
import com.java.utils.DropdownUtils;

@RequestMapping("dropdown")
@Controller
public class CommonController {
	private Logger log = Logger.getLogger(CommonController.class);
	/**
	 * 获取下拉框信息
	 * 2018-12-21 17:06:14
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public List<ViewDropCfg> dropdownCfgQuery(@RequestBody SearchParamObject searchParamObject){
		String dropdownName = searchParamObject.getSearchObj().get("dropdownName");
		log.info("下拉框的名称: "+dropdownName);
		List<ViewDropCfg> dropList = DropdownUtils.getDropCfg(dropdownName);
		return dropList;
	}
}
