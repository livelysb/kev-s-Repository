package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.zuplay.model.dto.PlayerDTO;
import com.kosta.zuplay.model.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginServiceImpl;
	
	@RequestMapping(value="firstLoginCheck",method=RequestMethod.POST)
	public String firstLoginCheck(PlayerDTO playerDTO,HttpSession session){
		boolean firstLogin=loginServiceImpl.firstLoginCheck(playerDTO.getPlayerNaverId());
		session.setAttribute("firstLogin", firstLogin);
		
		return "/";
	}
	
	@RequestMapping(value="")
}
