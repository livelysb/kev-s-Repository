package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

public interface PlayerStockService {	
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname);
	
	/**
	 * 플레이어가 가진 한 주식의 수량을 가져오기
	 * */
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd);
	
	/**
	 * 플레이어가 가진 한 주식의 수량을 수정하기
	 * */
	public boolean setPlayerStock(String playerNickname, String isuCd, int plQuantity);
		
}
