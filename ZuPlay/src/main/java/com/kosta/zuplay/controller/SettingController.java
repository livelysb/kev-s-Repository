package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.SettingDTO;
import com.kosta.zuplay.model.service.system.SettingService;

@Controller
public class SettingController {
	@Autowired
	private SettingService settingServiceImpl;
	
	/**
	 * 세팅정보 저장
	 */
	@RequestMapping("settingSave")
	@ResponseBody
	public boolean settingSave(HttpSession session,SettingDTO dto){
		dto.setPlayerNickname((String) session.getAttribute("playerNickname"));
		return settingServiceImpl.settingSave(dto);
	}
	
	/**
	 * 세팅정보 초기화
	 */
	@RequestMapping("settingReset")
	@ResponseBody
	public boolean settingReset(HttpSession session){
		String playerNickname=(String) session.getAttribute("playerNickname");
		return settingServiceImpl.settingReset(playerNickname);
	}
	
	/**
	 * 세팅정보 불러오기
	 */
	@RequestMapping("settingSelect")
	@ResponseBody
	public String settingSelect(HttpSession session){
		String playerNickname=(String) session.getAttribute("playerNickname");
		SettingDTO dto = settingServiceImpl.settingSelect(playerNickname);
		Gson gson = new Gson();
		String json=gson.toJson(dto);
		System.out.println(json);
		return json;
	}
}
