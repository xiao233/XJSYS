package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.UserInfDao;
import com.java.entites.UserInf;
import com.java.service.UserInfService;

@Service
public class UserInfServiceImpl implements UserInfService {
	@Autowired
	private UserInfDao userInfDao;
	@Override
	public boolean checkUserInf(UserInf userInf) {
		// TODO Auto-generated method stub
		return false;
	}

}
