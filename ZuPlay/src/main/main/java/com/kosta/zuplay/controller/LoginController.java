package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginServiceImpl;
	
	@RequestMapping(value="firstLoginCheck",method=RequestMethod.POST)
	public String firstLogin(PlayerDTO playerDTO,HttpSession session){
		boolean firstLogin=loginServiceImpl.firstLoginCheck(playerDTO.getPlayerNaverId());
		session.setAttribute("firstLogin", firstLogin);  //true硫� 理쒖큹濡쒓렇�씤, false硫� 湲곗〈�쉶�썝
		return "Login";
	}
	
	@RequestMapping(value="join")
	public String join(PlayerDTO playerDTO, HttpSession session){
		boolean join=loginServiceImpl.joinMember(playerDTO);
		session.setAttribute("join",join );
		return "/";
	}
}
