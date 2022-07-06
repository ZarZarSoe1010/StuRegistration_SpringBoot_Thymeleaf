package com.StudentRegistration.model;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
@Component
public class UserBean {
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String cpwd;
	@NotEmpty
	private String userRole;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpwd() {
		return cpwd;
	}
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public UserBean() {
		
	}
	public UserBean(String id,String name, String email, String password, String cpwd, String userRole) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpwd = cpwd;
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "id="+id+"name=" + name + ", email=" + email + ", password=" + password + ", cpwd=" + cpwd
				+ ", userRole=" + userRole ;
	}
	
	
	

}
