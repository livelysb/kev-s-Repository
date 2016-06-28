package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.service.community.PlayerInfo;

@Service
public class StockTradeServiceImpl implements StockTradeService {

	@Autowired
	private PlayerStockInfo playerStockInfo;

	@Autowired
	private PlayerInfo playerInfo;

	@Autowired
	private StockInfo stockInfo;

	/**
	 * 주식 판매
	 */
	@Transactional
	@Override
	public boolean sellStock(String playerNickname, String isuCd, int quantity) {
		int plQuantity = playerStockInfo.getPlayerStock(playerNickname, isuCd).getPlQuantity();
		int playerMoney = playerInfo.getPlayer(playerNickname).getPlayerMoney();
		int price = (int) (stockInfo.getPrice(isuCd).getTrdPrc() * quantity * (1 - 0.00315)); // 수수료
																								// 0.315%
																								// ->
																								// 0.000315
		if (plQuantity >= quantity) {
			
			if (playerStockInfo.setPlayerStock(playerNickname, isuCd, plQuantity - quantity))// 수량 빼기
				if (playerStockInfo.setPlayerMoney(playerNickname, playerMoney + price))// 돈 추가하기 ( 수수료 계산 )
					if(playerStockInfo.WriteStockHistory(playerNickname, isuCd, plQuantity, price, "s"))
						return true;
		}

		return false;
	}

	/**
	 * 주식 구매
	 */
	@Transactional
	@Override
	public boolean buyStock(String playerNickname, String isuCd, int plQuantity) {
		int playerMoney = playerInfo.getPlayer(playerNickname).getPlayerMoney();
		int price = (int) stockInfo.getPrice(isuCd).getTrdPrc();
		int totalPrice = (int) (price * plQuantity * 1.00015); // 총 금액
		int quantity = playerStockInfo.getPlayerStock(playerNickname, isuCd).getPlQuantity();// 현재
																								// 보유량

		if (playerMoney >= totalPrice)
			if (playerStockInfo.setPlayerMoney(playerNickname, playerMoney - (totalPrice))) // 돈
																							// 빼기
																							// (수수료
																							// 0.015%
																							// 포함
																							// ->
																							// 0.00015)
				if (playerStockInfo.setPlayerStock(playerNickname, isuCd, quantity + plQuantity)) // 주식수량
					if(playerStockInfo.WriteStockHistory(playerNickname, isuCd, plQuantity, totalPrice, "b"))																				// 늘려주기
					return true;
		return false;
	}

	
	
	
	

}
