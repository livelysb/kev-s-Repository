package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerInfoDAO {

	/**
	 * 플레이어가 가지고있는 기본정보 가져오기 ( PLAYER Table )
	 * */
	public PlayerDTO getPlayer(String playerNickname);
	
	/**
	 * 모든 플레이어의 닉네임 가져오기
	 * */
	public List<String> getAllPlayerNickName();
}
