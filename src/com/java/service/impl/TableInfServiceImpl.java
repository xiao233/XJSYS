package com.java.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.constants.CodeMsgConstants;
import com.java.dao.TableInfDao;
import com.java.entites.CodeMessageResult;
import com.java.entites.TblTableInf;
import com.java.service.TableInfService;

@Service
public class TableInfServiceImpl implements TableInfService {
	
	private Logger log = Logger.getLogger(TableInfServiceImpl.class);

	@Autowired
	private TableInfDao tableDao;
	@Override
	public CodeMessageResult<TblTableInf> queryAll() {
		
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		String code = "";
		String msg = "";
		try {
		List<TblTableInf> list = tableDao.queryAll();
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

}
