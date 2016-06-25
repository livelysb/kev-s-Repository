package com.kosta.zuplay.model.service;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerInfoService {

	
	/**
	 * 플레이어가 가지고있는 기본정보 가져오기 ( PLAYER Table )
	 * */
	public PlayerDTO getPlayer(String playerNickname);
	
	/**
	 * 플레이어의 사이버머니 수정하기
	 * */
	public boolean changePlayerMoney(String playerNickname, double money);
	
	/**
	 * 플레이어의 주식 수량 수정하기
	 * */
	public boolean changePlayerStock(String playerNickname, String isuCd, int plQuantity);
}
