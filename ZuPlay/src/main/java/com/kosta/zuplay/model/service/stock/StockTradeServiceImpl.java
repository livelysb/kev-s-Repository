package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.service.PlayerInfoService;

@Service
public class StockTradeServiceImpl implements StockTradeService {
	
	@Autowired
	private PlayerStockInfo playerStockInfo;
	
	@Autowired
	private PlayerInfoService playerInfo;
	
	@Autowired
	private StockInfo stockInfo;
	/**
	 * 주식 판매
	 * */
	@Transactional
	@Override
	public boolean SellStock(String playerNickname, String isuCd, int quantity) {
		int plQuantity = playerStockInfo.getPlayerStock(playerNickname, isuCd).getPlQuantity();
		if(plQuantity >= quantity) {
			//수량 빼기
			
			//돈 추가하기 ( 수수료 계산 )
		}
		
		return false;
	}

	
	/**
	 * 주식 구매
	 * */
	@Transactional
	@Override
	public boolean BuyStock(String playerNickname, String isuCd, int plQuantity) {
		int playerMoney = playerInfo.getPlayer(playerNickname).getPlayerMoney();
		int price = (int)stockInfo.getPrice(isuCd).getTrdPrc();
		int totalPrice = price*plQuantity;
		if(playerMoney >= totalPrice) 
			if(playerInfo.changePlayerMoney(playerNickname, -totalPrice)) //돈 빼기
				if(playerInfo.changePlayerStock(playerNickname, isuCd, plQuantity)) //주식수량 늘려주기
					return true;				
		return false;
	}

}
