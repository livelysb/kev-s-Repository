package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.service.ItemStoreService;

@Controller
public class ItemStoreController {
	@Autowired
	private ItemStoreService itemStoreServiceImpl;

	/**
	 * 상점아이템 리스트 가져오기
	 */
	@RequestMapping("itemStoreSelect")
	@ResponseBody
	public String itemStoreSelect(HttpSession session, String itemClass, int page) {
		String playerNickname = (String) session.getAttribute("playerNickname");
		List<ItemDTO> list = itemStoreServiceImpl.itemStoreSelect(playerNickname, itemClass, page);
		Gson gson = new Gson();
		String json=gson.toJson(list);
		return json;
	}
}
