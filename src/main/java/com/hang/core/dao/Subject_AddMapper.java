package com.hang.core.dao;

import java.util.List;

import com.hang.core.entity.Subject_Add;
import com.hang.core.entity.Subject_Cate;

public interface Subject_AddMapper {
	
	int saveCustomSubject(Subject_Add obj) throws Exception;
	
	int deleteCustomSubject(Subject_Add obj) throws Exception;
	
	List<Subject_Add> listCustomSubjectByUserId(Short id) throws Exception;
	
	Integer countSubjAddByUserIdAndSubjId(Subject_Add key) throws Exception;
	
	List<Subject_Cate> listSubjectCategory() throws Exception;
}	
