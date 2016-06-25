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
	 @RequestMapping(value="itemStoreSelect" ,produces="text/plain;charset=UTF-8" )
	 @ResponseBody
	public String itemStoreSelect(HttpSession session, String itemClass, int page) {
		 System.out.println(itemClass);
		 System.out.println(page);
		String playerNickname = (String) session.getAttribute("playerNickname");
		List<ItemDTO> list = itemStoreServiceImpl.itemStoreSelect(playerNickname, itemClass, page);
		Gson gson = new Gson();
		String json=gson.toJson(list);
		return json;
	}
	
	/**
	 * 상점 아이템 구매하기
	 */
	 @RequestMapping(value="itemStoreBuy" ,produces="text/plain;charset=UTF-8" )
	@ResponseBody
	public int itemStoreBuy(HttpSession session, ItemDTO itemDTO, int quantity){
		String playerNickname = (String) session.getAttribute("playerNickname");
		int result=itemStoreServiceImpl.itemStoreBuy(playerNickname, itemDTO, quantity);// 1=정상 / 2=인벤토리부족 / 3=루비부족
		
		return result;
	}
	 /**
	  * 상점 아이템 판매하기
	  */
	 @RequestMapping(value="itemStoreSell" ,produces="text/plain;charset=UTF-8" )
		@ResponseBody
		public boolean itemStoreSell(HttpSession session,int piSq,String itemCode){
			String playerNickname = (String) session.getAttribute("playerNickname");
			return itemStoreServiceImpl.itemStoreSell(playerNickname, piSq, itemCode);
	 }
}
