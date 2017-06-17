package com.hang.core.dao;

import java.util.List;

import com.hang.core.entity.Bookset;

public interface BooksetMapper {
	//通过主键删除bookset
    int deleteByPrimaryKey(Short booksetid)  throws Exception;

    int insert(Bookset record);

    int insertSelective(Bookset record);

    Bookset selectByPrimaryKey(Short booksetid);

    int updateByPrimaryKeySelective(Bookset record);

    int updateByPrimaryKey(Bookset record);
    
    //通过userID获取用户账套信息
	List<Bookset> listBooksetByUserId(short id)  throws Exception;
	//通过bookset获取booksetId
	Bookset getBooksetByUserIdAndCompName(Bookset bookset)  throws Exception;
}