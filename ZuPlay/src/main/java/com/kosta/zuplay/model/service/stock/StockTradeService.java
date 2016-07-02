package com.kosta.zuplay.model.service.stock;

/**
 * 주식거래 시 발생하는 이벤트 처리
 * */
public interface StockTradeService {
	
	/**
	 * 주식 판매
	 * */
	public boolean sellStock(String playerNickname, String isuCd, int plQuantity) throws Exception;
	
	/**
	 * 주식 구매
	 * */
	public boolean buyStock(String playerNickname, String isuCd, int plQuantity) throws Exception;
	
}
