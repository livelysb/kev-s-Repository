package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.service.item.ItemStoreService;

@Controller
public class ItemStoreController {
	@Autowired
	private ItemStoreService itemStoreServiceImpl;

	/**
	 * 상점아이템 리스트 가져오기
	 */
	@RequestMapping(value = "itemStoreSelect", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String itemStoreSelect(HttpSession session, String itemClass, int page) throws Exception {
		System.out.println(itemClass);
		System.out.println(page);
		String playerNickname = (String) session.getAttribute("playerNickname");
		List<ItemDTO> list;
		try {
			list = itemStoreServiceImpl.itemStoreSelect(playerNickname, itemClass, page);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}

	/**
	 * 상점 아이템 구매하기
	 */
	@RequestMapping(value = "itemStoreBuy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String itemStoreBuy(HttpSession session, ItemDTO itemDTO, int quantity) throws Exception {
		String playerNickname = (String) session.getAttribute("playerNickname");
		String result;
		try {
			result = itemStoreServiceImpl.itemStoreBuy(playerNickname, itemDTO, quantity);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		} // 1=정상 / 2=인벤토리부족 / 3=루비부족
		return result;
	}

	/**
	 * 상점 아이템 판매하기
	 */
	@RequestMapping(value = "itemStoreSell", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public boolean itemStoreSell(HttpSession session, int piSq, String itemCode) throws Exception {
		String playerNickname = (String) session.getAttribute("playerNickname");
		try {
			return itemStoreServiceImpl.itemStoreSell(playerNickname, piSq, itemCode);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
}
