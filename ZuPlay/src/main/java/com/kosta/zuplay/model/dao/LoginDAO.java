package com.kosta.zuplay.model.dao;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginDAO {
	PlayerDTO firstLoginCheck(String naverId);
	int joinMember(PlayerDTO playerDTO);
	String checkRepetiton(String playerNickname);
	String getNickname(String playerNaverId);
	String getGender(String playerNickname);
}
