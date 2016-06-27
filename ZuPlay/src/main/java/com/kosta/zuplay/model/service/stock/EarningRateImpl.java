package com.kosta.zuplay.model.service.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.service.PlayerInfo;

@Service
public class EarningRateImpl implements EarningRate {

	@Autowired
	private PlayerInfo playerInfo;
	
	@Autowired
	private PlayerStockInfo playerStockInfo;
	
	@Autowired
	private StockInfo stockInfo;
	/**
	 * 금액을 통한 해당시즌 수익률 구하기
	 * 현재 금액, 보유주식의 현재 체결가를 이용한 금액
	 * */
	@Override
	public double calEarningRate(String playerNickname) {
		int startMoney = 100000000;
		int curruntMoney = playerInfo.getPlayer(playerNickname).getPlayerMoney();
		List<PlayerListsDTO> playerLists =  playerStockInfo.getPlayerStocks(playerNickname);
		for(PlayerListsDTO playerListsDTO : playerLists) {
			curruntMoney += stockInfo.getPrice(playerListsDTO.getIsuCd()).getTrdPrc() * playerListsDTO.getPlQuantity();
		}
		int m = (int)((curruntMoney - startMoney) / startMoney * 10000.0);
		return m /100.0;
	}

	/**
	 * 플레이어의 일일 수익률 계산하기
	 * */
	@Override
	public double calDailyEarningRate(String playerNickname) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 플레이어의 종목별 수익률 계산하기
	 * */
	@Override
	public double calItemEarningRate(String playerNickname, String isuCd) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 플레이어의 분야별 수익률 계산하기
	 * */
	@Override
	public double calKindEarningRate(String playerNickname, int kind) {
		// TODO Auto-generated method stub
		return 0;
	}

}
