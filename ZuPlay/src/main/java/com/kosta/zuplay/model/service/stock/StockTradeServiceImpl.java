package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Service
public class StockTradeServiceImpl implements StockTradeService {

	@Autowired
	private PlayerStockService playerStockService;

	@Autowired
	private PlayerInfoService playerInfoService;

	@Autowired
	private StockInfoService stockInfoService;
	
	@Autowired
	private DealHistoryService dealHistoryService;

	@Transactional
	@Override
	public boolean sellStock(String playerNickname, String isuCd, int quantity) throws Exception{
		int plQuantity = playerStockService.getPlayerStock(playerNickname, isuCd).getPlQuantity();
		int playerMoney = playerInfoService.getPlayer(playerNickname).getPlayerMoney();
		int price = (int) (stockInfoService.getPrice(isuCd).getTrdPrc() * quantity * (1 - 0.00315)); // 수수료
																								// 0.315%
																								// ->
																								// 0.000315
		if (plQuantity >= quantity) {
			if (playerStockService.setPlayerStock(playerNickname, isuCd, plQuantity - quantity))// 수량 빼기
				if (playerInfoService.setPlayerMoney(playerNickname, playerMoney + price))// 돈 추가하기 ( 수수료 계산 )
					if(dealHistoryService.stockHistoryInsert(playerNickname, isuCd, plQuantity, price, "s"))
						return true;
		}

		return false;
	}

	@Transactional
	@Override
	public boolean buyStock(String playerNickname, String isuCd, int plQuantity) throws Exception{
		int playerMoney = playerInfoService.getPlayer(playerNickname).getPlayerMoney();
		int price = (int) stockInfoService.getPrice(isuCd).getTrdPrc();
		int totalPrice = (int) (price * plQuantity * 1.00015); // 총 금액
		int quantity = playerStockService.getPlayerStock(playerNickname, isuCd).getPlQuantity();// 현재
																								// 보유량
		if (playerMoney >= totalPrice)
			if (playerInfoService.setPlayerMoney(playerNickname, playerMoney - (totalPrice))) // 돈
																							// 빼기
																							// (수수료
																							// 0.015%
																							// 포함
																							// ->
																							// 0.00015)
				if (playerStockService.setPlayerStock(playerNickname, isuCd, quantity + plQuantity)) // 주식수량
					if(dealHistoryService.stockHistoryInsert(playerNickname, isuCd, plQuantity, totalPrice, "b"))																				// 늘려주기
					return true;
		return false;
	}

	
	
	
	

}
