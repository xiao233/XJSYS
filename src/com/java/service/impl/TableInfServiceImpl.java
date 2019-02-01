package com.java.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.constants.CodeMsgConstants;
import com.java.dao.TableInfDao;
import com.java.entites.TblTableInf;
import com.java.entites.common.CodeMessageResult;
import com.java.service.TableInfService;
import com.java.utils.StringUtils;

@Service
public class TableInfServiceImpl implements TableInfService {
	
	private Logger log = Logger.getLogger(TableInfServiceImpl.class);

	@Autowired
	private TableInfDao tableDao;
	@Override
	public CodeMessageResult<TblTableInf> queryAll(TblTableInf tableInf) {
		
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		String code = "";
		String msg = "";
		try {
			List<TblTableInf> list = tableDao.queryAll(tableInf);
			if(list==null||list.size()==0) {
				code = CodeMsgConstants.QUERY_SUCC_NO_RECORD;
				msg = CodeMsgConstants.QUERY_SUCC_NO_RECORD_MSG;
			}else {
				code = CodeMsgConstants.QUERY_SUCC_HAS_RECORD;
				msg = CodeMsgConstants.QUERY_SUCC_HAS_RECORD_MSG;
				rs.setResult(list);
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			code = CodeMsgConstants.QUERY__FAILD;
			msg = CodeMsgConstants.QUERY__FAILD_MSG;
		}
		log.info(code+": "+msg);
		rs.setCode(code);
		rs.setMsg(msg);
		return rs;
	}
	
	@Override
	public CodeMessageResult<TblTableInf> queryPageAll(TblTableInf tableInf) {
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		String code = "";
		String msg = "";
		try {
			
			int dataTotal = tableDao.queryCount(tableInf);
			if(dataTotal<1) {
				code = CodeMsgConstants.QUERY_SUCC_NO_RECORD;
				msg = CodeMsgConstants.QUERY_SUCC_NO_RECORD_MSG;
			}else {
				List<TblTableInf> list = tableDao.queryPageAll(tableInf);
				rs.getPage().setDataTotal(dataTotal);
				rs.getPage().setPageTotal((int)Math.ceil(dataTotal*1.0/tableInf.getPageSize()));
				
				code = CodeMsgConstants.QUERY_SUCC_HAS_RECORD;
				msg = CodeMsgConstants.QUERY_SUCC_HAS_RECORD_MSG;
				rs.setResult(list);
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			code = CodeMsgConstants.QUERY__FAILD;
			msg = CodeMsgConstants.QUERY__FAILD_MSG;
		}
		log.info(code+": "+msg);
		rs.setCode(code);
		rs.setMsg(msg);
		return rs;
	}
	
	@Override
	public CodeMessageResult<TblTableInf> updateTableInfById(TblTableInf tableInf) {
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		if(tableInf==null || StringUtils.isEmpty(tableInf.getTableId()+"")) {
			log.error("����ʧ��: ��idΪ��!");
			rs.setCode(CodeMsgConstants.UPDATE_FAILD);
			rs.setMsg("����ʧ��: ��id����Ϊ��!");
			return rs;
		}
		try {
			tableDao.updateTableInfById(tableInf);
		}catch (Exception e) {
			log.error("�����쳣:"+e.getMessage());
			rs.setCode(CodeMsgConstants.UPDATE_FAILD);
			rs.setMsg("�������ݿ������쳣!");
			return rs;
		}
		rs.setCode(CodeMsgConstants.UPDATE_SUCCESS);
		return rs;
	}
	
	@Override
	public CodeMessageResult<TblTableInf> deleteTableInf(TblTableInf tableInf) {
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		if(tableInf==null || (StringUtils.isEmpty(tableInf.getTableId()+"")
				&&StringUtils.isEmpty(tableInf.getTableName())
				&&StringUtils.isEmpty(tableInf.getTableCrtUsr()))) {
			log.error("ɾ��ʧ��: ����ϢΪ��!");
			rs.setCode(CodeMsgConstants.DELETE_FAILD);
			rs.setMsg("ɾ��ʧ��: ����Ϊ��!");
			return rs;
		}
		try {
			tableDao.deleteTableInf(tableInf);
		}catch (Exception e) {
			log.error("ɾ���쳣:"+e.getMessage());
			rs.setCode(CodeMsgConstants.DELETE_FAILD);
			rs.setMsg("ɾ�����ݿ������쳣��");
			return rs;
		}
		rs.setCode(CodeMsgConstants.DELETE_SUCCESS);
		return rs;
	}

	@Override
	public CodeMessageResult<TblTableInf> createTableInfById(TblTableInf tableInf) {
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		if(tableInf==null || StringUtils.isEmpty(tableInf.getTableName())) {
			log.error("���ʧ��: ����Ϊ��!");
			rs.setCode(CodeMsgConstants.CREATE_FAILD);
			rs.setMsg("���ʧ��: ��������Ϊ��!");
			return rs;
		}
		try {
			tableDao.createTableInf(tableInf);
		}catch (Exception e) {
			log.error("����쳣:"+e.getMessage());
			rs.setCode(CodeMsgConstants.CREATE_FAILD);
			rs.setMsg("������ݵ����ݿ��쳣��");
			return rs;
		}
		rs.setCode(CodeMsgConstants.CREATE_SUCCESS);
		return rs;
	}
}
