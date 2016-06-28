package com.kosta.zuplay.model.service.player;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerInfo {

	/**
	 * 플레이어 정보 가져오기
	 * */
	public PlayerDTO getPlayer(String playerNickname);

	
	/**
	 * 모픈 플레이어의 닉네임 가져오기
	 * */
	public List<String> getAllPlayerNickName();

}
