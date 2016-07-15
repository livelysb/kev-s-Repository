package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;
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
	public boolean sellStock(String playerNickname, String isuCd, int quantity) throws Exception {
		int plQuantity = playerStockService.getPlayerStock(playerNickname, isuCd).getPlQuantity();
		int playerMoney = playerInfoService.getMoney(playerNickname);
		int price = (int) (stockInfoService.getPrice(isuCd).getTrdPrc() * quantity * (1 - 0.00315)); // 수수료
		// 0.315%
		// ->
		// 0.000315
		if (plQuantity >= quantity && quantity >= 0) {
			if (playerStockService.setPlayerStock(playerNickname, isuCd, plQuantity - quantity))// 수량
																								// 빼기
				if (playerInfoService.setPlayerMoney(playerNickname, playerMoney + price))// 돈
																							// 추가하기
																							// (
																							// 수수료
																							// 계산
																							// )
					if (dealHistoryService.stockHistoryInsert(playerNickname, isuCd, quantity, price, "s"))
						return true;
		}

		return false;
	}

	@Transactional
	@Override
	public boolean buyStock(String playerNickname, String isuCd, int plQuantity) throws Exception {
		PriceDTO priceDTO = stockInfoService.getPrice(isuCd);
		MasterDTO masterDTO = stockInfoService.getStockDetail(playerNickname, isuCd);
		if (priceDTO.getTrdPrc() > masterDTO.getUplmtprc() || priceDTO.getTrdPrc() < masterDTO.getLwlmtprc())
			return false;
		int playerMoney = playerInfoService.getMoney(playerNickname);
		int price = (int) stockInfoService.getPrice(isuCd).getTrdPrc();
		int totalPrice = (int) (price * plQuantity * 1.00015); // 총 금액
		int quantity = 0;
		PlayerListsDTO playerListsDTO = playerStockService.getPlayerStock(playerNickname, isuCd);// 현재보유량
		if (playerListsDTO != null)
			quantity = playerListsDTO.getPlQuantity();
		if (playerMoney >= totalPrice && plQuantity >= 0)
			if (playerInfoService.setPlayerMoney(playerNickname, playerMoney - (totalPrice))) // 돈빼기
																								// (수수료
																								// 0.015%
																								// 포함
																								// ->
																								// 0.00015)
				if (playerStockService.setPlayerStock(playerNickname, isuCd, quantity + plQuantity)) // 주식수량
					if (dealHistoryService.stockHistoryInsert(playerNickname, isuCd, plQuantity, totalPrice, "b")) // 늘려주기
						return true;
		return false;
	}

}
