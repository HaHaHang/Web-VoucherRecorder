package com.hang.core.service;

import java.util.List;

import com.hang.core.entity.Bookset;

public interface BooksetService {
	//select all booksetinfo by userid
	List<Bookset> listBooksetByUserId(short id) throws Exception;
	//delete bookset by booksetid
	int deleteByPrimaryKey(Short booksetid) throws Exception;
	//get Bookset  by Bookset(compname&userid)
	Bookset insertAndReturnForBookset(Bookset bookset) throws Exception;
	
}
