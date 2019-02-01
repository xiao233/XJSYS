package com.java.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.constants.CodeMsgConstants;
import com.java.constants.CommonConstants;
import com.java.dao.UserInfDao;
import com.java.entites.UserInf;
import com.java.entites.common.CodeMessageResult;
import com.java.service.UserInfService;
import com.java.utils.GetUUID;
import com.java.utils.RedisUtils;
import com.java.utils.StringUtils;

@Service
public class UserInfServiceImpl implements UserInfService {
	private Logger log = Logger.getLogger(UserInfServiceImpl.class);
	@Autowired
	private UserInfDao userInfDao;
	
	@Override
	public CodeMessageResult<UserInf> loginRegister(UserInf userInf) {
		CodeMessageResult<UserInf> cms = checkUserInf(userInf);
		String code ="";
		String msg = "";
		try {
			if(cms.getCode().equals(CodeMsgConstants.L_LOGIN_SUCC)) {
				msg+="登录";
				List<UserInf> list = userInfDao.checkUserInf(userInf);
				if(list!=null && list.size()>0) {
					cms.setResult(list);
					msg=CodeMsgConstants.L_LOGIN_SUCC_MSG;
				}else {
					code=CodeMsgConstants.L_LOGIN_FAIL;
					msg=CodeMsgConstants.L_LOGIN_FAIL_MSG;
				}
			}else if(cms.getCode().equals(CodeMsgConstants.R_REGISTER_SUCC)){
				msg="注册";
				fillDefaultValues(userInf);
				userInfDao.register(userInf);
				msg=CodeMsgConstants.R_REGISTER_SUCC_MSG;
			}else {
				return cms;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			code=CodeMsgConstants.OPERATION_FAIL;
			msg+=CodeMsgConstants.OPERATION_FAIL_MSG;
		}
		
		if(!StringUtils.isEmpty(code)) {
			cms.setCode(code);
		}
		if(!StringUtils.isEmpty(msg)) {
			cms.setMsg(msg);
		}
		
		return cms;
	}

	private CodeMessageResult<UserInf> checkUserInf(UserInf userInf) {
		CodeMessageResult<UserInf> cms = new CodeMessageResult<UserInf>();
		String code = null;
		String msg = null;
		
		if(StringUtils.isEmpty(userInf.getUserName())) {
			code=CodeMsgConstants.L_R_NAME_IS_EMPTY;
			msg=CodeMsgConstants.L_R_NAME_IS_EMPTY_MSG;
			cms.setCode(code);
			cms.setMsg(msg);
			return cms;
		}
		
		if(StringUtils.isEmpty(userInf.getUserPaw())) {
			code=CodeMsgConstants.L_R_PWD_ISEMPTY;
			msg = CodeMsgConstants.L_R_PWD_ISEMPTY_MSG;
			cms.setCode(code);
			cms.setMsg(msg);
			return cms;
		}
		
		//验证图片验证码
		String enterCodeImage = userInf.getCodeImage();
		String codeKey = userInf.getCodeKey();
		if(StringUtils.isEmpty(enterCodeImage)) {//输入的图片验证码为空
			code=CodeMsgConstants.CODE_IS_EMPTY;
			msg=CodeMsgConstants.CODE_IS_EMPTY_MSG;
		}else if(StringUtils.isEmpty(codeKey)){//保存图片验证码的key为空
			code=CodeMsgConstants.CODE_REDIS_ERROR;
			msg=CodeMsgConstants.CODE_REDIS_ERROR_MSG;
		}else {
			String codeImage = RedisUtils.getString(codeKey);
			if(StringUtils.isEmpty(codeImage)) {//从redis获取图片验证码为空
				code=CodeMsgConstants.CODE_REDIS_ERROR;
				msg=CodeMsgConstants.CODE_REDIS_ERROR_MSG;
			}else if(!codeImage.toLowerCase().equals(enterCodeImage.toLowerCase())){//输入验证码不对
				code=CodeMsgConstants.CODE_IS_ERROR;
				msg=CodeMsgConstants.CODE_IS_ERROR_MSG;
			}
		}
		
		if(code!=null) {
			cms.setCode(code);
			cms.setMsg(msg);
			return cms;
		}
		//到这图片验证码输入正确
		
		
		if(userInf.getIsLogin().equals(CommonConstants.IS_LOGIN)) {
			code=CodeMsgConstants.L_LOGIN_SUCC;
		}else {
			if(userInf.getUserPaw().equals(userInf.getUserPawNew())) {
				code=CodeMsgConstants.R_REGISTER_SUCC;
			}else {
				code=CodeMsgConstants.R_REGISTER_FAIL_PAW_DIFFERENT;
				msg=CodeMsgConstants.R_REGISTER_FAIL_PAW_DIFFERENT_MSG;
			}
		}
		cms.setCode(code);
		cms.setMsg(msg);
		
		
		return cms;
	}

	/**
	 * 当属性为null填充初始值
	 * 2018-09-12 17:33:45
	 * @param userInf
	 */
	private void fillDefaultValues(UserInf userInf) {
		if(userInf.getBackUrl()==null) {
			userInf.setBackUrl("");
		}
		if(userInf.getConfirmFlag()==null) {
			userInf.setConfirmFlag("0");
		}
		if(userInf.getSysid()==null) {
			userInf.setSysid("");
		}
		if(userInf.getUserName()==null) {
			userInf.setUserName("");
		}
		if(userInf.getUserPaw()==null) {
			userInf.setUserPaw("");
		}
		if(userInf.getUserSeq()==null) {
			userInf.setUserSeq(GetUUID.getUUID(0, 32));
		}
		
	}

}
