package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.system.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginServiceImpl;
	
	@RequestMapping(value={"Login", "LoginInfo"})
	public void login(){}
	
	/**
	 * 최초로그인 확인
	 * @param playerNaverId
	 * @return true=최초로그인/false=기존회원로그인
	 */
	@RequestMapping(value="firstLoginCheck",method=RequestMethod.POST)
	@ResponseBody
	public boolean firstLoginCheck(String playerNaverId,HttpSession session){
		System.out.println(playerNaverId);
		boolean firstLogin=loginServiceImpl.firstLoginCheck(playerNaverId);
		if(firstLogin==false){
			session.setAttribute("playerNickname", loginServiceImpl.getNickname(playerNaverId));
		}
		return firstLogin;
	}
	
	
	/**
	 * 회원가입
	 * @param playerNickname
	 * @param playerNaverId
	 * @param playerGender
	 * @param playerAge
	 * @return true=회원정보 DB 삽입 성공/false=회원정보 DB 삽입 실패
	 */
	@RequestMapping(value="joinMember")
	public String joinMember(String playerNickname,String playerNaverId,String playerGender,String playerAge,HttpSession session){
		System.out.println(playerNickname);
		boolean joinMember=loginServiceImpl.joinMember(new PlayerDTO(playerNickname, playerNaverId, playerGender, playerAge, 0, 0, 0, "마스터", 1, 1, 1, 1));
		System.out.println("[ LOG ] : 회원가입 성공 " +joinMember);
		session.setAttribute("playerNickname", playerNickname);
		return "index";
	}
	
	/**
	 * 닉네임 중복체크
	 * @param playerNickname
	 * @return true=사용가능/false=사용불가
	 */
	@RequestMapping(value="checkRepetition")
	@ResponseBody
	public boolean checkRepetition(String playerNickname){
		boolean checkRepetiton=loginServiceImpl.checkRepetition(playerNickname);
		return checkRepetiton;
		
	}
	
	/**
	 * 로그아웃 (세션삭제)
	 */
	@RequestMapping("logout")
	@ResponseBody
	public void logout(HttpSession session){
		session.invalidate();
	}
}
