package com.hang.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hang.core.dao.SubjectMapper;
import com.hang.core.dao.Subject_AddMapper;
import com.hang.core.entity.Subject_Add;
import com.hang.core.service.SubjectAddService;
@Service
public class SubjectAddServiceImpl implements SubjectAddService {
	
	@Autowired
	Subject_AddMapper mapper;
	@Autowired
	SubjectMapper subjectMapper;

	@Override
	public List<Subject_Add> listAllCustomSubject(Short id) throws Exception {
		return mapper.listCustomSubjectByUserId(id);
	}

	@Override
	public int saveCustomSubject(Subject_Add key) throws Exception {
		return mapper.saveCustomSubject(key);
	}

	@Override
	public int deleteCustomSubject(Subject_Add key) throws Exception {
		return mapper.deleteCustomSubject(key);
	}

	@Override
	public boolean getCountOfSubject(Subject_Add key) throws Exception {
		if(subjectMapper.countOfSubjBySubjId(key.getSubjectid()) > 0) {
			return true;
		}else if(mapper.countSubjAddByUserIdAndSubjId(key) > 0) {
			return true;
		}else{
			return false;
		}
	}
}
