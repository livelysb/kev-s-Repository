package com.kosta.zuplay.model.service.stock;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockTradeServiceImpl implements StockTradeService {

	/**
	 * 주식 판매
	 * */
	@Transactional
	@Override
	public boolean SellStock(String playerNickname, String isuCd, int plQuantity) {
		
		return false;
	}

	
	/**
	 * 주식 구매
	 * */
	@Transactional
	@Override
	public boolean BuyStock(String playerNickname, String isuCd, int plQuantity) {
		return false;
	}

}
