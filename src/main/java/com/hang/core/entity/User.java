package com.hang.core.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Short userid;

    private String username;

    private String password;

    private String telephone;

    private String name;

    private String email;

    private static final long serialVersionUID = 1L;

    public Short getUserid() {
        return userid;
    }

    public void setUserid(Short userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", telephone=" + telephone
				+ ", name=" + name + ", email=" + email + "]";
	} 
}