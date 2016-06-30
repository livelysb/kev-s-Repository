package com.kosta.zuplay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.item.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryServiceImpl;

	/**
	 * 보유 아이템 리스트 조회
	 */
	 @RequestMapping(value="playerItemSelectAll" ,produces="text/plain;charset=UTF-8" )
	@ResponseBody
	public String playerItemSelectAll(HttpSession session) {
		String playerNickname = (String) session.getAttribute("playerNickname");
		System.out.println("playerNickname : " + playerNickname);

		List<PlayerItemDTO> list = inventoryServiceImpl.playerItemSelectAll(playerNickname);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);

		return "null".equals(json) ? "{}" : json;

	}

	/**
	 * 아이템 목록 업데이트
	 */
	 @RequestMapping(value="playerItemInsert" ,produces="text/plain;charset=UTF-8" )
	@ResponseBody
	public boolean playerItemInsert(HttpSession session, List<PlayerItemDTO> jsonList) {
		String playerNickname = (String) session.getAttribute("playerNickname");
		ArrayList<PlayerItemDTO> list = new ArrayList<PlayerItemDTO>();
		for (int i = 0; i < jsonList.size(); i++) {
			list.add(new PlayerItemDTO(jsonList.get(i).getPiSq(), playerNickname, jsonList.get(i).getItemCode(),
					jsonList.get(i).getPiIsused(), jsonList.get(i).getPiIndex(), null));
		}
		return inventoryServiceImpl.playerItemInsert(list);
	}
}
