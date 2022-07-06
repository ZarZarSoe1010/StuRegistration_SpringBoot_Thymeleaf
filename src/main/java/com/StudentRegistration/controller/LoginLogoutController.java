package com.StudentRegistration.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.StudentRegistration.dao.UserDAO;
import com.StudentRegistration.dto.UserResponseDTO;
import com.StudentRegistration.model.StudentBean;
import com.StudentRegistration.model.UserBean;


@Controller
public class LoginLogoutController {
	@Autowired
	private UserDAO userDao;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() { 
		return new ModelAndView("LGN001","userBean",new UserBean());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("stuBean") @Validated UserBean userBean, BindingResult bs,
			HttpSession session,ModelMap model) {		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = formatter.format(date);		
		List<UserResponseDTO> userResList = userDao.selectAllUser();
		for (UserResponseDTO userInfo : userResList) {
			if (userInfo.getUid().equals(userBean.getId()) && userInfo.getPassword().equals(userBean.getPassword())) {
				session.setAttribute("userInfo", userInfo);
				session.setAttribute("date", currentDate);			
				return new ModelAndView("MNU001");			
			} 		
	}
		model.addAttribute("msg", "ID and password do not match");
		return new ModelAndView("LGN001","userBean",userBean);			
}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(ModelMap model, HttpSession session) {
		session.removeAttribute("userInfo");
		session.invalidate();
		return new ModelAndView("LGN001","userBean",new UserBean());
	}	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showWelcome() {
		return "MNU001";
	}
	

}
