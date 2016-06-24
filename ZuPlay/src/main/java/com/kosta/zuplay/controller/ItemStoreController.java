package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.kosta.zuplay.model.dto.item.ItemDTO;

@Controller
public class ItemStoreController {
	/**
	 * 상점아이템 리스트 가져오기
	 */
	public String itemStoreSelect(HttpSession session, String itemClass,int page){
		String playerNickname=(String) session.getAttribute("playerNickname");
		return null;
	}
}
