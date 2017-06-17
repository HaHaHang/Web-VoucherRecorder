package com.hang.core.service;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.hang.core.entity.Subject_Add;


public interface SubjectAddService {
	
	List<Subject_Add> listAllCustomSubject(Short id) throws Exception;
	
	int saveCustomSubject(Subject_Add key) throws Exception;
	
	int deleteCustomSubject(Subject_Add key) throws Exception;
	
	boolean getCountOfSubject(Subject_Add key) throws Exception;
}
