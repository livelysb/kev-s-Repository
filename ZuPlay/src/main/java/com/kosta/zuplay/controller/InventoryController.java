package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.InventoryService;
import com.kosta.zuplay.model.service.InventoryServiceImpl;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryServiceImpl;
	/**
	 * 보유 아이템 리스트 조회
	 */
	@RequestMapping("playerItemSelectAll")
	@ResponseBody
	public List<PlayerItemDTO> playerItemSelectAll(HttpSession session) {
		String playerNickname = (String) session.getAttribute("playerNickname");
		System.out.println("playerNickname : " + playerNickname);
		return inventoryServiceImpl.playerItemSelectAll(playerNickname);
		
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
