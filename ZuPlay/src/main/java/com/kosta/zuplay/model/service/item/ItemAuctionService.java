package com.kosta.zuplay.model.service.item;

import java.util.List;

import com.kosta.zuplay.model.dto.item.ItemMarketDTO;

public interface ItemAuctionService {
	/**
	 * search item in item auction
	 * @param keyword : What you want to search
	 * @param itemClass : If you want to search for some parts
	 * @param page : eight units in one page
	 */
	List<ItemMarketDTO> auctionSearch(String keyword,String itemClass,int page);
	
	/**
	 * buy item in item auction
	 * return int(1=success/2=not enough inventory/3=not enough Ruby/4=sold out item)
	 */
	int auctionBuy(String playerNickname,int imSq);
	
	/**
	 * sell item in item auction
	 * return boolean(true=success/fail=fail)
	 */
	boolean auctionSell(String playerNickname,int piSq,int imPurchasePrice);
	
	/**
	 * 경매장 경매 취소
	 */
	boolean auctionCancel(int imSq);
	
	/**
	 * 경매장 유찰품/골드 수령									
	 */
	boolean auctionBring(String playerNickname,int imSq);
	
	/**
	 * 내 경매 물품 가져오기
	 */
	List<ItemMarketDTO> auctionMyPage(String playerNickname);
	/**
	 * 경매종료 물품 종료 
	 */
	public void itemAuctionUpdate();
}