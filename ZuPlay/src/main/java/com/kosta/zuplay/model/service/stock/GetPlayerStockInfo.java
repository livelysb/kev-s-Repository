package com.kosta.zuplay.model.service.stock;

import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

public interface GetPlayerStockInfo {
	
		
	/**
	 * 플레이어가 가지고있는 기본정보 가져오기 ( PLAYER Table )
	 * */
	public PlayerDTO getPlayer(String playerNickname);
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public PlayerListsDTO getStockOfPlayer(String playerNickname);
	
	/**
	 * 플레이어가 
	 * */
	
}
