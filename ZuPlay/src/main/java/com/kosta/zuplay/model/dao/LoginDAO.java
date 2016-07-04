package com.kosta.zuplay.model.dao;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginDAO {
	PlayerDTO firstLoginCheck(String naverId) throws Exception;
	int joinMember(PlayerDTO playerDTO) throws Exception;
	String checkRepetiton(String playerNickname) throws Exception;
	String getNickname(String playerNaverId) throws Exception;
	String getGender(String playerNickname) throws Exception;
}
