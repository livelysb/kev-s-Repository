package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping({"/", "index"})
	public String index(HttpSession session){
		return "index";
	}
	
	
	
	@RequestMapping("{url}")
	public void call(HttpSession session){
		
	}
}
