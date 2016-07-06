package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Controller
public class IndexController {

	@Autowired
	PlayerInfoService playerInfoService;
	
	@RequestMapping({ "/", "index" })
	public ModelAndView index(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("index");
		try {
			mv.addObject("firstLoginToday", playerInfoService.getRubyPerDay((String)session.getAttribute("playerNickname"))) ;
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
		
		return mv;
	}

	@RequestMapping("{url}")
	public void call(HttpSession session) {
	}
}
