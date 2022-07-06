package com.StudentRegistration.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
@Component
public class StudentBean {
	@NotEmpty
		private String id;
	@NotEmpty
		private String name;
	@NotEmpty
		private String dob;
	@NotEmpty
		private String gender;
	@NotEmpty
		private String phone;
	@NotEmpty
		private String education;
	private List<CourseBean>courses;
	@NotEmpty
		private List<String> stuCourse;
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
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public List<CourseBean> getCourses() {
			return courses;
		}
		public void setCourses(List<CourseBean> courses) {
			this.courses = courses;
		}
		public List<String> getStuCourse() {
			return stuCourse;
		}
		public void setStuCourse(List<String> stuCourse) {
			this.stuCourse = stuCourse;
		}
		public StudentBean() {
			
		}
		public StudentBean(@NotEmpty String id, @NotEmpty String name, @NotEmpty String dob, @NotEmpty String gender,
				@NotEmpty String phone, @NotEmpty String education, List<CourseBean> course,
				@NotEmpty List<String> stuCourse) {
			super();
			this.id = id;
			this.name = name;
			this.dob = dob;
			this.gender = gender;
			this.phone = phone;
			this.education = education;
			this.courses = course;
			this.stuCourse = stuCourse;
		}
		@Override
		public String toString() {
			return "StudentBean [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", phone="
					+ phone + ", education=" + education + ", courses=" + courses + ", stuCourse=" + stuCourse + "]";
		}


}
