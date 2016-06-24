package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

public interface PlayerStockInfo {	
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname);
	
	/**
	 * 플레이어가 가진 한 주식의 수량만을 가져오기
	 * */
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd);
	
	/**
	 * 플레이어의 일일 수익률 계산해서 insert 하기
	 * */
	public double getEarningRate(String playerNickname);

	
	/**
	 * 플레이어의 종목별 수익률 계산하기
	 * */
	
	
	
	/**
	 * 플레이어의 분야별 수익률 계산하기
	 * */
	
}
