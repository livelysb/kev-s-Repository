package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;

public interface DealHistoryService {

	/**
	 * 주식 거래 히스토리 쓰기
	 * */
	public boolean WriteStockHistory(String playerNickname, String isuCd, int plQuantity, int price, String bs);
	
	
	/**
	 * 주식 거래 히스토리 가져오기
	 * */
	public List<StockDealHistoryDTO> getStockHistory(String playerNickname);
	
}
