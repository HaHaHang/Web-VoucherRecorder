package com.hang.core.util;

import java.util.List;

public class JudgmentUtil {
	
	private JudgmentUtil(){};
	
	public static Boolean collectionIsEmpty(List list){
		if(list != null && !list.isEmpty()) 
			return false;
		else
			return true;
	}
}
