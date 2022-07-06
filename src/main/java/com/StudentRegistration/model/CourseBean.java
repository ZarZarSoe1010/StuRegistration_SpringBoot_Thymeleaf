package com.StudentRegistration.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
@Component
public class CourseBean {
	@NotEmpty
	private String id;
	@NotEmpty
	private String name;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CourseBean() {
		
	}

	public CourseBean(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}