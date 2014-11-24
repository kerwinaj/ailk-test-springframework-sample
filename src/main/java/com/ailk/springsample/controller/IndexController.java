package com.ailk.springsample.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ailk.springsample.model.User;

@Controller
public class IndexController {
	private static final Log log = LogFactory.getLog(IndexController.class);

	@RequestMapping("/index")
	public ModelAndView index(@ModelAttribute User user, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
}
