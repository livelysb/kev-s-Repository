package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginServiceImpl;
	
	@RequestMapping(value={"Login", "LoginInfo"})
	public void login(){
	}
	
	@RequestMapping(value="firstLoginCheck",method=RequestMethod.POST)
	@ResponseBody
	public boolean firstLoginCheck(String playerNaverId){
		System.out.println(playerNaverId);
		boolean firstLogin=loginServiceImpl.firstLoginCheck(playerNaverId);
		return firstLogin;
	}
	
	@RequestMapping(value="joinMember")
	public String joinMember(PlayerDTO playerDTO, HttpSession session){
		boolean joinMember=loginServiceImpl.joinMember(playerDTO);
		session.setAttribute("joinMember",joinMember );
		return "/";
	}
	
	@RequestMapping(value="checkRepetition")
	@ResponseBody
	public boolean checkRepetition(String playerNickname){
		boolean checkRepetiton=loginServiceImpl.checkRepetition(playerNickname);
		return checkRepetiton;
		
	}
}
