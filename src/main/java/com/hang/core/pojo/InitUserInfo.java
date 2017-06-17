package com.hang.core.pojo;

import java.io.Serializable;
import java.util.List;

import com.hang.core.entity.Subject;
import com.hang.core.entity.Subject_Cate;
import com.hang.core.entity.User;

public class InitUserInfo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7314557077348631944L;
	//user info
	private User user;
	//accounting subjects
	List<Subject> subjlist;
	
	List<Subject_Cate> subjcatelist;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Subject> getSubjlist() {
		return subjlist;
	}
	public void setSubjlist(List<Subject> subjlist) {
		this.subjlist = subjlist;
	}
	public List<Subject_Cate> getSubjcatelist() {
		return subjcatelist;
	}
	public void setSubjcatelist(List<Subject_Cate> subjcatelist) {
		this.subjcatelist = subjcatelist;
	}
	
	
}
