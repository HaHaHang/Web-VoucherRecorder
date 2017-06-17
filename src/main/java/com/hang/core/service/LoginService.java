package com.hang.core.service;

import com.hang.core.entity.User;
import com.hang.core.pojo.InitUserInfo;

public interface LoginService {
	
	/*
	 * Login module: Initialize user information
	 * get userinfo and subjectlist by userid
	 */
	InitUserInfo initUserInfo(short userid) throws Exception;
	
	/*
	 * Login module: Account check
	 * get userid by username and password
	 */
	short getUserIdByUnAndPw(User user) throws Exception;
}
