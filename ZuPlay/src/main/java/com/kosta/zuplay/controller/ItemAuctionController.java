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
	
	/**
	 * 경매장 검색
	 * @param keyword
	 * @param itemClass
	 * @param page
	 * @return
	 */
	@RequestMapping(value="auctionSearch",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String auctionSearch(HttpSession session, String keyword,String itemClass, int page) throws Exception{
		System.out.println(keyword);
		System.out.println(itemClass);
		System.out.println(page);
		
		List<ItemMarketDTO> list = null;
		try {
			list = itemAuctionServiceImpl.auctionSearch(keyword, itemClass, page);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
		Gson gson=new Gson();
		String json =gson.toJson(list);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 경매장 구매
	 * @param session
	 * @param imSq
	 * @return
	 */
	@RequestMapping(value="auctionBuy",produces="application/json;charset=UTF-8")
	@ResponseBody
	public int auctionBuy(HttpSession session,int imSq) throws Exception { 
		String playerNickname=(String) session.getAttribute("playerNickname");
		int result;
		try {
			result = itemAuctionServiceImpl.auctionBuy(playerNickname, imSq);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
		return result;
	}
	
	/**
	 * 경매장 물품 등록
	 * @param session
	 * @param piSq
	 * @param imPurchasePrice
	 * @return
	 */
	@RequestMapping(value="auctionSell",produces="application/json;charset=UTF-8")
	@ResponseBody
	public boolean auctionSell(HttpSession session,int piSq,int imPurchasePrice) throws Exception{
		String playerNickname=(String) session.getAttribute("playerNickname");
		try {
			return itemAuctionServiceImpl.auctionSell(playerNickname, piSq, imPurchasePrice);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
	}
	
	/**
	 * 경매장 물품 취소
	 * @param imSq
	 * @return
	 */
	@RequestMapping(value="auctionCancel",produces="application/json;charset=UTF-8")
	@ResponseBody
	public boolean auctionCancel(HttpSession session, int imSq) throws Exception{
		try {
			return itemAuctionServiceImpl.auctionCancel(imSq);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
	}
	
	/**
	 * 유찰/골드 수령
	 */
	@RequestMapping(value="auctionBring",produces="application/json;charset=UTF-8")
	@ResponseBody
	public boolean auctionBring(HttpSession session, int imSq) throws Exception{
		System.out.println("imSq : " + imSq);
		String playerNickname=(String)session.getAttribute("playerNickname");
		try {
			return itemAuctionServiceImpl.auctionBring(playerNickname, imSq);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
	}
	
	/**
	 * 내판매목록
	 * @param session
	 * @return
	 */
	@RequestMapping(value="auctionMyPage",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String auctionMyPage(HttpSession session) throws Exception{
		String playerNickname=(String)session.getAttribute("playerNickname");
		List<ItemMarketDTO> list = null;
		try {
			list = itemAuctionServiceImpl.auctionMyPage(playerNickname);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
