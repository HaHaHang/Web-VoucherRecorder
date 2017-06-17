package com.hang.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hang.core.dao.BooksetMapper;
import com.hang.core.entity.Bookset;
import com.hang.core.service.BooksetService;
@Service
public class BooksetServiceImpl implements BooksetService {

	@Autowired
	BooksetMapper booksetMapper;
	
	@Override
	public List<Bookset> listBooksetByUserId(short id) throws Exception{
		return booksetMapper.listBooksetByUserId(id);
	}
	
	@Override
	public int deleteByPrimaryKey(Short booksetid) throws Exception{
		return booksetMapper.deleteByPrimaryKey(booksetid);
	}
	
	@Override
	public Bookset insertAndReturnForBookset(Bookset bookset) throws Exception{
		booksetMapper.insert(bookset);
		return booksetMapper.getBooksetByUserIdAndCompName(bookset);
	}
	
}
