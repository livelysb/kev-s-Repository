package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.item.ItemMarketDTO;
import com.kosta.zuplay.model.service.item.ItemAuctionService;

@Controller
public class ItemAuctionController {
	@Autowired
	private ItemAuctionService itemAuctionServiceImpl;
	
	@RequestMapping("auctionSearch")
	@ResponseBody
	public String auctionSearch(String keyword,String itemClass, int page){
		List<ItemMarketDTO> list = itemAuctionServiceImpl.auctionSearch(keyword, itemClass, page);
		Gson gson=new Gson();
		String json =gson.toJson(list);
		System.out.println(json);
		return json;
	}
	
	@RequestMapping("auctionBuy")
	@ResponseBody
	public int auctionBuy(HttpSession session,String imSq){
		String playerNickname=(String) session.getAttribute("playerNickname");
		int result = itemAuctionServiceImpl.auctionBuy(playerNickname, imSq);
		return result;
	}
	
	@RequestMapping("auctionSell")
	@ResponseBody
	public boolean auctionSell(HttpSession session,String piSq,int imPurchasePrice){
		String playerNickname=(String) session.getAttribute("playerNickname");
		return itemAuctionServiceImpl.auctionSell(playerNickname, piSq, imPurchasePrice);
	}
	
	@RequestMapping("auctionCancel")
	@ResponseBody
	public boolean auctionCancel(String imSq){
		return itemAuctionServiceImpl.auctionCancel(imSq);
	}
	
	@RequestMapping("auctionBring")
	@ResponseBody
	public boolean auctionBring(HttpSession session,String imSq){
		String playerNickname=(String)session.getAttribute("playerNickname");
		return itemAuctionServiceImpl.auctionBring(playerNickname, imSq);
	}
}
