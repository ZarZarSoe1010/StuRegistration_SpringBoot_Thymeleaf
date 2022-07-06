package com.StudentRegistration.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.StudentRegistration.dto.CourseRequestDTO;
import com.StudentRegistration.dto.CourseResponseDTO;
@Repository
public class CourseDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insertCourse(CourseRequestDTO dto) {
		int result = 0;
		String sql = "insert into course(cid,name)values(?,?)";
		result= jdbcTemplate.update(sql,
				dto.getCid(),dto.getName());
		return result;
	}
	public List<CourseResponseDTO> selectAllCourse() {
		String sql = "select * from course";	
		List<CourseResponseDTO>courseList= jdbcTemplate.query(sql,(rs,rowNum) ->new CourseResponseDTO(
				rs.getString("cid"),
				rs.getString("name")));
		return courseList;
	}

}
