package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.item.InventoryService;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Controller
public class PlayerInfoController {

	
	@Autowired
	private PlayerInfoService playerInfoService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value={"playerInfoSelectAll"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String playerInfoSelectAll(HttpSession session, String keyword) throws Exception{
		List<PlayerDTO> list;
		try {
			list = playerInfoService.playerInfoSelectAll(keyword);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	@RequestMapping(value={"updatePI"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updatePI(HttpSession session) throws Exception {
		try {
			String playerNickname = (String) session.getAttribute("playerNickname");
			PlayerDTO playerDTO = playerInfoService.getPlayer(playerNickname);
			Gson gson = new Gson();
			String json = gson.toJson(playerDTO);
			return json;
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	@RequestMapping(value={"userInfo"})
	public ModelAndView userInfo(HttpSession session, String targetPlayer) throws Exception {
		ModelAndView mv = new ModelAndView("userInfo");
		String playerNickname = (String) session.getAttribute("playerNickname");
		PlayerDTO playerDTO = playerInfoService.getPlayerDetail(playerNickname);
		if(playerDTO.getPlayerGender().equals("M"))
			playerDTO.setPlayerGender("남성");
		else
			playerDTO.setPlayerGender("여성");
		List<PlayerItemDTO> playerItemList = inventoryService.playerItemSelectAll(playerNickname);
		mv.addObject("playerDTO", playerDTO);
		mv.addObject("playerItemList", playerItemList);
		
		if(playerDTO.getLikerList().contains(playerNickname)) {
			mv.addObject("isLike", "true");
		}else {
			mv.addObject("isLike","false");
		}
		
		mv.addObject("likeNum",playerDTO.getLikerList().size());
		//mv.addObject("isOn", (context.getAttribute(playerNickname));
		
		return mv;
	}
	
	@RequestMapping(value={"playerStock"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String playerStock(HttpSession session) {
		String playerNickname = (String)session.getAttribute("playerNickname");
		Gson gson = new Gson();
		String json = gson.toJson(null);
		return json;
	}
}
