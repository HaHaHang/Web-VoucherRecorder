package com.hang.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hang.core.dao.SubjectMapper;
import com.hang.core.dao.Subject_AddMapper;
import com.hang.core.dao.UserMapper;
import com.hang.core.entity.Subject;
import com.hang.core.entity.Subject_Cate;
import com.hang.core.entity.User;
import com.hang.core.pojo.InitUserInfo;
import com.hang.core.service.LoginService;
import com.hang.core.util.EntityUtil;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	SubjectMapper subjectMapper;
	@Autowired
	Subject_AddMapper addMapper;
	
	
	@Override
	public InitUserInfo initUserInfo(short userid) throws Exception{
		//容器
		InitUserInfo initUserInfo = new InitUserInfo();
		//获取设置用户信息
		initUserInfo.setUser(userMapper.selectByPrimaryKey(userid));
		//获取设置会计科目
		List<Subject> list = subjectMapper.listSubject();
		list.addAll(EntityUtil.subject_AddToSubject(addMapper.listCustomSubjectByUserId(userid)));
		initUserInfo.setSubjlist(list);
		//获取设置会计科目类别
		List<Subject_Cate> add =  addMapper.listSubjectCategory();
		initUserInfo.setSubjcatelist(add);
		return initUserInfo;
	}

	@Override
	public short getUserIdByUnAndPw(User user) throws Exception{
		Short flag = userMapper.getUserIdByUnAndPw(user); 
		if(flag != null)
			return flag;
		else
			return -1;
	}

}
