package com.StudentRegistration.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.StudentRegistration.dto.UserRequestDTO;
import com.StudentRegistration.dto.UserResponseDTO;

@Repository
public class UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertUser(UserRequestDTO dto) {
		int result = 0;
		String sql = "insert into user (uid,name,email,password,cpwd,userRole) values(?,?,?,?,?,?)";
		result = jdbcTemplate.update(sql, dto.getUid(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getCpwd(),
				dto.getUserRole());
		return result;
	}

	public int updateUser(UserRequestDTO dto) {
		int result = 0;
		String sql = "update user set name=?,email=?,password=?,cpwd=?,userRole=? where uid=?";
		result = jdbcTemplate.update(sql, dto.getName(), dto.getEmail(), dto.getPassword(), dto.getCpwd(),
				dto.getUserRole(), dto.getUid());
		return result;
	}

	public int deleteUser(String userId) {
		int result = 0;
		String sql = "delete from user where uid=? ";
		result = jdbcTemplate.update(sql, userId);
		return result;
	}

	public List<UserResponseDTO> selectAllUser() {
		String sql = "select * from user";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new UserResponseDTO(
				rs.getString("uid"), 
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("password"),
				rs.getString("cpwd"),
				rs.getString("userRole")));
	}

	public UserResponseDTO selectOneUser(String userId) {
		String sql = "select * from user where uid=?";
		return jdbcTemplate.queryForObject(sql,
				(rs, rowNum) -> new UserResponseDTO(
						rs.getString("uid"), 
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("cpwd"), 
						rs.getString("userRole")),userId);
	}

	public List<UserResponseDTO> selectByFilter(UserRequestDTO dto) {
		String userId = dto.getUid().isBlank() ? dto.getUid() : ("%" + dto.getUid() + "%");
		String userName= dto.getName().isBlank() ? dto.getName(): ("%" + dto.getName() + "%");
		String sql = "select * from user where uid like ? or name like ?";
		
		return jdbcTemplate.query(sql,(rs, rowNum) ->new UserResponseDTO(
						rs.getString("uid"),
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("password"), 
						rs.getString("cpwd"), 
						rs.getString("userRole")),
						userId, userName);
		
	/*	List<UserResponseDTO>res=jdbcTemplate.query(sql,(rs,rowNum) -> {
			   UserResponseDTO userDto = new UserResponseDTO();
			  userDto.setUid(rs.getString("uid"));
			  return userDto;
			});
		return res;*/
	}
}







