package com.kosta.zuplay.model.service.stock;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

public interface PlayerStockInfo {	
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public PlayerListsDTO getStockOfPlayer(String playerNickname);
	
	/**
	 * 플레이어의 전체 수익률 계산하기
	 * */
	public double getEarningRate(String playerNickname);
	
	/**
	 * 플레이어의 종목별 수익률 계산하기
	 * */
	
	
	/**
	 * 플레이어의 분야별 수익률 계산하기
	 * */
	
}
