package com.hang.core.dao;

import java.util.List;

import com.hang.core.entity.Bookset;

public interface BooksetMapper {
	//ͨ������ɾ��bookset
    int deleteByPrimaryKey(Short booksetid)  throws Exception;

    int insert(Bookset record);

    int insertSelective(Bookset record);

    Bookset selectByPrimaryKey(Short booksetid);

    int updateByPrimaryKeySelective(Bookset record);

    int updateByPrimaryKey(Bookset record);
    
    //ͨ��userID��ȡ�û�������Ϣ
	List<Bookset> listBooksetByUserId(short id)  throws Exception;
	//ͨ��bookset��ȡbooksetId
	Bookset getBooksetByUserIdAndCompName(Bookset bookset)  throws Exception;
}