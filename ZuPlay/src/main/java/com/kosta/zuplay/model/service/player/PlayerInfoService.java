package com.kosta.zuplay.model.service.player;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerInfoService {

	/**
	 * 플레이어 정보 가져오기
	 * */
	public PlayerDTO getPlayer(String playerNickname);

	
	/**
	 * 모픈 플레이어의 닉네임 가져오기
	 * */
	public List<String> getAllPlayerNickName();
	
	/**
	 * 플레이어의 사이버머니 수정하기
	 * */
	public boolean setPlayerMoney(String playerNickname, int playerMoney);
	
	/**
	 * 주식을 포함한 현재 자산 구하기
	 * */
	public int getTotalMoney(String playerNickname);

}
