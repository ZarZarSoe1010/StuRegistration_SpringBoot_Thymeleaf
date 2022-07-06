package com.StudentRegistration.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.StudentRegistration.dto.StudentRequestDTO;
import com.StudentRegistration.dto.StudentResponseDTO;


@Repository
public class StudentDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertStudent(StudentRequestDTO dto) {
		int result = 0;
		String sql = "insert into student(sid,name,dob,gender,phone,education)values(?,?,?,?,?,?)";
		result = jdbcTemplate.update(sql,
				dto.getSid(),
				dto.getName(),
				dto.getDob(), 
				dto.getGender(), 
				dto.getPhone(),
				dto.getEducation());
		return result;
	}
	public int updateStudent(StudentRequestDTO dto) {
		int result = 0;
		String sql = "update student set name=?,dob=?,gender=?,phone=?,education=? where sid=?";
		result = jdbcTemplate.update(sql, 
				dto.getName(), 
				dto.getDob(),
				dto.getGender(), 
				dto.getPhone(),
				dto.getEducation(), 
				dto.getSid());
		return result;
	}
	public int deleteStudent(String studentId) {
		int result = 0;
		String sql = "delete from student where sid=?";
		result = jdbcTemplate.update(sql, studentId);
		return result;
	}
	
	public List<StudentResponseDTO> selectAllStudent() {
		 String sql = "select * from student"; 
		 List<StudentResponseDTO>res=jdbcTemplate.query(sql,(rs,rowNum) -> {
			 StudentResponseDTO stuDto = new StudentResponseDTO();
			  stuDto.setSid(rs.getString("sid"));
			  stuDto.setName(rs.getString("name"));
			  stuDto.setDob(rs.getString("dob"));
			  stuDto.setGender(rs.getString("gender"));
			  stuDto.setPhone(rs.getString("phone"));
			  stuDto.setEducation(rs.getString("education"));
			  return stuDto;
			});
		return res;
	}

	public StudentResponseDTO selectOneStudent(String studentId) {
		String sql = "select * from student where sid=?";
	StudentResponseDTO res=jdbcTemplate.queryForObject(sql,(rs,rowNum) -> {
			 StudentResponseDTO stuDto = new StudentResponseDTO();
			  stuDto.setSid(rs.getString("sid"));
			  stuDto.setName(rs.getString("name"));
			  stuDto.setDob(rs.getString("dob"));
			  stuDto.setGender(rs.getString("gender"));
			  stuDto.setPhone(rs.getString("phone"));
			  stuDto.setEducation(rs.getString("education"));
			  return stuDto;
			},studentId);
		return res;
				
	}
	public void insertStudent_Course(String sid, List<String> cIds) {
		for (String cid : cIds) {
			int result = 0;
			String sql = "insert into student_course(sid,cid)values(?,?)";
			result = jdbcTemplate.update(sql, sid, cid);
		}
	}	
	
	public int deleteStudent_Course( String studentId) {
		int result = 0;
		String sql = "delete from student_course where sid=?";
		result = jdbcTemplate.update(sql, studentId);
		return result;
	}
	public List<String> selectCourseIdList(String studentId) {
		String sql = "select cid from student_course where sid=?";
		return jdbcTemplate.query(sql,(rs, rowNum)->
		rs.getString("cid"),studentId);
	}
	
	public List<String> selectCourseNameList(String studentId) {
		List<String> courseList = new ArrayList<String>();
		try {
			String sql = "select cid from student_course where sid=?";
			List<String>courseIdList= jdbcTemplate.query(sql,(rs, rowNum)->rs.getString("cid"),studentId);			
			for(String cid: courseIdList) {
				 sql="select name from course where cid=?";
				 String courseName=jdbcTemplate.queryForObject(sql, (rs,rowNum)->rs.getString("name"),cid  );			
				 courseList.add(courseName);
			}
		} catch (Exception e) {
			System.out.println("Database selectByfilter error!!");
		}
		return  courseList;
	}
	
	
	public List<StudentResponseDTO> selectStudentListByIdOrNameOrCourse(String id, String name, String course) {
		String stuId = id.isBlank() ? id : ("%" + id + "%");
		String stuName= name.isBlank() ? name: ("%" + name + "%");
		String stuCourse = course.isBlank() ? course : ("%" + course + "%");
		
		String sql = " select distinct s.sid,s.name,c.name from student s join student_course sc \r\n"
				+ " on s.sid=sc.sid \r\n" + "join course c \r\n" + " on sc.cid=c.cid\r\n"
				+ " where s.sid like ? or s.name like ? or c.name like ? ;";
		
		List<StudentResponseDTO>studentList=jdbcTemplate.query(sql,(rs,rowNum) -> {
			StudentResponseDTO stuDto=new StudentResponseDTO();
			  stuDto.setSid(rs.getString("sid"));
			  stuDto.setName(rs.getString("name"));
			return stuDto;		
		},stuId,stuName,stuCourse);		
		
		  List<StudentResponseDTO>value=studentList.stream().distinct().collect(Collectors.toList());
		  return value;
		 
	}	
}
