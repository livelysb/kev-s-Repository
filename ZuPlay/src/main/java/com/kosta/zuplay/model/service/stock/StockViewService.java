package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.ListsDTO;

/**
 * 하는 일
 * 1. 플레이어가 가지고 있는 주식 정보 보여주기
 * 2. 주식메뉴에서 필요한 주식정보 보여주기
 * 3. 경매장에 가지는 주식정보 보여주기
 * */
public interface StockViewService {
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public List<ListsDTO> getStockOfPlayer(String playerNickname);
}
