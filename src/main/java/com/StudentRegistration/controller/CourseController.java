package com.StudentRegistration.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.StudentRegistration.dao.CourseDAO;
import com.StudentRegistration.dto.CourseRequestDTO;
import com.StudentRegistration.dto.CourseResponseDTO;
import com.StudentRegistration.model.CourseBean;


@Controller
public class CourseController {
	@Autowired
	private CourseDAO courseDAO;
	@RequestMapping(value = "/setupRegisterCourse", method = RequestMethod.GET)
	public ModelAndView setupRegisterCourse() {
		CourseBean courseBean= new CourseBean();
		List<CourseResponseDTO> courseList =courseDAO.selectAllCourse();
		if (courseList.size() == 0) {
			courseBean.setId("COU001");
		} else {
			int tempId = Integer.parseInt(courseList.get(courseList.size() - 1).getCid().substring(3)) + 1;
			String courseId = String.format("COU%03d", tempId);
			courseBean.setId(courseId);
		}
		return new ModelAndView("BUD003","courseBean",courseBean);
	}
	@RequestMapping(value = "/registerCourse", method = RequestMethod.POST)
	public String registerCourse(@ModelAttribute("courseBean") @Validated CourseBean courseBean, BindingResult bs,
			ModelMap model) {
		if (bs.hasErrors()) {
			return "BUD003";
		}
			CourseRequestDTO reqDto = new CourseRequestDTO();
			reqDto.setCid(courseBean.getId());
			reqDto.setName(courseBean.getName());	
			int i=courseDAO.insertCourse(reqDto);
			if(i>0) {
			model.addAttribute("msg", "Register Succesful !!");
			 courseBean=new CourseBean();
			List<CourseResponseDTO> courseList = courseDAO.selectAllCourse();
			if (courseList.size() == 0) {
				courseBean.setId("COU001");
			} else {
				int tempId = Integer.parseInt(courseList.get(courseList.size() - 1).getCid().substring(3)) + 1;
				String courseId = String.format("COU%03d", tempId);
				courseBean.setId(courseId);
			}
			}else {
				model.addAttribute("msg","Register Failed!");
			}
			model.addAttribute("courseBean",courseBean);
			return "BUD003";
		}
	

}
