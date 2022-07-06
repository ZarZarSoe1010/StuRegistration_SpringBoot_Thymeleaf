package com.StudentRegistration.dto;

public class UserResponseDTO {
	private String uid;
	private String name;
	private String email;
	private String password;
	private String cpwd;
	private String userRole;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public UserResponseDTO() {
		
	}
	public UserResponseDTO(String uid, String name, String email, String password, String cpwd, String userRole) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpwd = cpwd;
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "uid="+uid+"name=" + name + ", email=" + email + ", password=" + password + ", cpwd=" + cpwd
				+ ", userRole=" + userRole ;
	}

}
