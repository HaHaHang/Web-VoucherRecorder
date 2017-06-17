package com.hang.core.dao;

import com.hang.core.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Short userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Short userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //µÇÂ½ÕËºÅÑéÖ¤
    Short getUserIdByUnAndPw(User user) throws Exception;

}