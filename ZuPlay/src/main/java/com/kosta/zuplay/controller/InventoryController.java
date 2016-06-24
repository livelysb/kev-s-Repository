package com.kosta.zuplay.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryServiceImpl;
	/**
	 * 보유 아이템 리스트 조회
	 */
	@RequestMapping("playerItemSelectAll")
	@ResponseBody
	public String playerItemSelectAll(HttpSession session) {
		System.out.println("1111");
		String playerNickname = (String) session.getAttribute("playerNickname");
		System.out.println("playerNickname : " + playerNickname);
		
		List<PlayerItemDTO> list= inventoryServiceImpl.playerItemSelectAll(playerNickname);
		Gson gson = new Gson();
		Type type = new ArrayList<PlayerItemDTO>(){}.getClass();
		String json = gson.toJson(list, type);
		System.out.println(json);
		
		return json != null ? json : "{}";
		
	}

	/**
	 * 아이템 목록 업데이트
	 */
	@RequestMapping("playerItemInsert")
	@ResponseBody
	public boolean playerItemInsert(List<PlayerItemDTO> list){
		return inventoryServiceImpl.playerItemInsert(list);
	}
}
