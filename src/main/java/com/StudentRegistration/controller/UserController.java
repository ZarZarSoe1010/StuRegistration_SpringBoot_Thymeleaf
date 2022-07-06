package com.StudentRegistration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.StudentRegistration.dao.UserDAO;
import com.StudentRegistration.dto.UserRequestDTO;
import com.StudentRegistration.dto.UserResponseDTO;
import com.StudentRegistration.model.UserBean;

@Controller
public class UserController {
	@Autowired
	private UserDAO userDao;
	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String UserManagement(ModelMap model) {
		return "USR003";
	}

	@RequestMapping(value = "/setupRegisterUser", method = RequestMethod.GET)
	public ModelAndView setupRegisterUser() {
		return new ModelAndView("USR001", "userBean", new UserBean());
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult bs,
			ModelMap model) {
		if (bs.hasErrors()) {
			return "USR001";

		} else if (!userBean.getPassword().equals(userBean.getCpwd())) {
			model.addAttribute("msg", "Passwords do not match !!");
			model.addAttribute("userBean", userBean);
			return "USR001";
		}
		List<UserResponseDTO> userList = userDao.selectAllUser();
		if (userList.size() == 0) {
			userBean.setId("USR001");
		} else {
			int tempId = Integer.parseInt(userList.get(userList.size() - 1).getUid().substring(3)) + 1;
			String userId = String.format("USR%03d", tempId);
			userBean.setId(userId);
		}
		UserRequestDTO reqDto = new UserRequestDTO();
		reqDto.setUid(userBean.getId());
		reqDto.setEmail(userBean.getEmail());
		reqDto.setName(userBean.getName());
		reqDto.setPassword(userBean.getPassword());
		reqDto.setCpwd(userBean.getCpwd());
		reqDto.setUserRole(userBean.getUserRole());
		int i = userDao.insertUser(reqDto);
		if (i > 0) {
			model.addAttribute("msg", "Register Successful !!");
		} else {
			model.addAttribute("msg", "Register Failed!");
		}
		model.addAttribute("userBean", new UserBean());
		return "USR001";
	}

	@RequestMapping(value = "/setupUpdateUser", method = RequestMethod.GET)
	public ModelAndView setupUpdateUser(@RequestParam("selectedUserId")String id) {
		UserRequestDTO reqDto = new UserRequestDTO();
		reqDto.setUid(id);
		UserResponseDTO resDto = userDao.selectOneUser(id);
		UserBean userBean = new UserBean();
		userBean.setId(resDto.getUid());
		userBean.setName(resDto.getName());
		userBean.setEmail(resDto.getEmail());
		userBean.setPassword(resDto.getPassword());
		userBean.setCpwd(resDto.getCpwd());
		userBean.setUserRole(resDto.getUserRole());
		return new ModelAndView("USR002", "userBean", userBean);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult bs,
			ModelMap model) {
		System.out.println(userBean);
		if (bs.hasErrors()) {
			return "USR002";
		}
		UserRequestDTO reqDto = new UserRequestDTO();
		reqDto.setUid(userBean.getId());
		reqDto.setName(userBean.getName());
		reqDto.setEmail(userBean.getEmail());
		reqDto.setPassword(userBean.getPassword());
		reqDto.setCpwd(userBean.getCpwd());
		reqDto.setUserRole(userBean.getUserRole());
		int i = userDao.updateUser(reqDto);
		if (!userBean.getPassword().equals(userBean.getCpwd())) {
			model.addAttribute("msg", "Passwords do not match !!");
			return "USR002";
		}
		if (i > 0) {
			model.addAttribute("msg", "Update Successful!!");
		} else {
			model.addAttribute("msg", "Update fail!!");
		}
		return "USR002";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("selectedUserId")String id, ModelMap model) {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setUid(id);
		int i = userDao.deleteUser(id);
		if (i > 0) {
			model.addAttribute("msg", "Delete Successful");
		} else {
			model.addAttribute("msg", "Delete Fail");
		}
		return "USR003";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam("id") String uid, @RequestParam("name") String uname, ModelMap model) {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setUid(uid);
		dto.setName(uname);
		List<UserResponseDTO> userResList = new ArrayList<UserResponseDTO>();
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		if (uid.isBlank() && uname.isBlank()) {
			userResList = userDao.selectAllUser();
		} else {
			userResList = userDao.selectByFilter(dto);
		}
		if (userResList.size() == 0) {
			model.addAttribute("msg", "User not found!!");
		} else {
			for (UserResponseDTO userRes : userResList) {
				UserBean userBean = new UserBean();
				userBean.setId(userRes.getUid());
				userBean.setName(userRes.getName());
				userBean.setEmail(userRes.getEmail());
				userBean.setPassword(userRes.getPassword());
				userBean.setCpwd(userRes.getCpwd());
				userBean.setUserRole(userRes.getUserRole());
				userBeanList.add(userBean);
			}
		}
		model.addAttribute("userList", userBeanList);
		return "USR003";
	}

	
	
	
	
}

