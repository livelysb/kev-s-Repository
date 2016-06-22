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
	@ResponseBody
	public boolean joinMember(String playerNickname,String playerNaverId,String playerGender,String playerAge){
		boolean joinMember=loginServiceImpl.joinMember(new PlayerDTO(playerNickname, playerNaverId, playerGender, playerAge, 0, 0, 0, "마스터", 1, 1, 1, 1));
		return joinMember;
	}
	
	@RequestMapping(value="checkRepetition")
	@ResponseBody
	public boolean checkRepetition(String playerNickname){
		boolean checkRepetiton=loginServiceImpl.checkRepetition(playerNickname);
		return checkRepetiton;
		
	}
}
