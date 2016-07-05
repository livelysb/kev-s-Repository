package com.kosta.zuplay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.item.InventoryService;
import com.sun.media.jfxmedia.events.PlayerTimeListener;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryServiceImpl;

	/**
	 * 보유 아이템 리스트 조회
	 */
	@RequestMapping(value = "playerItemSelectAll", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String playerItemSelectAll(HttpSession session) throws Exception {
		String playerNickname = (String) session.getAttribute("playerNickname");
		System.out.println("playerNickname : " + playerNickname);

		List<PlayerItemDTO> list;
		try {
			list = inventoryServiceImpl.playerItemSelectAll(playerNickname);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);

		return "null".equals(json) ? "{}" : json;

	}

	/**
	 * 아이템 목록 업데이트
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value = "playerItemInsert", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public boolean playerItemInsert(HttpSession session, String itemParam) throws Exception {
		PlayerItemDTO[] jsonList = null;
		Gson gson = new Gson();
		try {
			jsonList = gson.fromJson(itemParam, PlayerItemDTO[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonList);
		String playerNickname = (String) session.getAttribute("playerNickname");
		ArrayList<PlayerItemDTO> list = new ArrayList<PlayerItemDTO>();
		for (int i = 0; i < jsonList.length; i++) {
			list.add(new PlayerItemDTO(jsonList[i].getPiSq(), playerNickname, jsonList[i].getItemCode(),
					jsonList[i].getPiIsused(), jsonList[i].getPiIndex(), null));
		}
		System.out.println(list);
		try {
			return inventoryServiceImpl.playerItemInsert(list);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
}
