package com.hang.core.util;

import java.util.ArrayList;
import java.util.List;

import com.hang.core.entity.Subject;
import com.hang.core.entity.Subject_Add;

public class EntityUtil {
	
	private EntityUtil(){};
	
	public static List<Subject> subject_AddToSubject(List<Subject_Add> list){
		List<Subject> l = new ArrayList<Subject>();
		Subject test ;
		for (Subject_Add li : list) {
			test = new Subject();
			test.setIsload(li.getIsload());
			test.setSubcatid(li.getSubcatid());
			test.setSubjectid(li.getSubjectid());
			test.setSubjectname(li.getSubjectname());
			l.add(test);
		}
		return l;
		
	}
}
