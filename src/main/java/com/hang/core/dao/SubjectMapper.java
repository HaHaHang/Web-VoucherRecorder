package com.hang.core.dao;

import java.util.List;

import com.hang.core.entity.Subject;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer subjectid);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer subjectid);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
    
    //�Զ�����
    //��ѯ���пƼ���Ŀ
    List<Subject> listSubject()  throws Exception;
    
    String getSubjectNameById(Integer id) throws Exception;
    
    Integer countOfSubjBySubjId(Integer subjectid)throws Exception;
}