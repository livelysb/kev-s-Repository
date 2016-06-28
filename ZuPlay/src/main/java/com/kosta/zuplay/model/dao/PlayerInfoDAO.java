package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

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
	/**
	 * 플레이어 루비 가져오기
	 */
	int getRuby(String playerNickname);
	
	/**
	 * 플레이어 루비 업데이트
	 */
	int updateRuby(Map<String, String> map);
}
