package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.zuplay.model.dto.player.SettingDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;
import com.kosta.zuplay.model.service.system.SettingService;

@Controller
public class IndexController {

	@Autowired
	PlayerInfoService playerInfoService;
	@Autowired
	SettingService settingServiceImpl;

	@RequestMapping({ "/", "index" })
	public ModelAndView index(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("index");
		try {
			if (session.getAttribute("playerNickname") != null) {
				SettingDTO settingDTO = settingServiceImpl
						.settingSelect((String) session.getAttribute("playerNickname"));
				mv.addObject("firstLoginToday",
						playerInfoService.getRubyPerDay((String) session.getAttribute("playerNickname")));
				mv.addObject("theme", settingDTO.getPsTheme());
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			System.out.println(e.getMessage());
			throw new Exception();
		}

		return mv;
	}
	
	@RequestMapping("intro")
	public void intro(){}

	@RequestMapping("{url}")
	public void call(HttpSession session) {
	}
}
