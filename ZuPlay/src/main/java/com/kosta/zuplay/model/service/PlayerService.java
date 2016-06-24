package com.kosta.zuplay.model.service;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerService {

	
	/**
	 * 플레이어가 가지고있는 기본정보 가져오기 ( PLAYER Table )
	 * */
	public PlayerDTO getPlayer(String playerNickname);
}
