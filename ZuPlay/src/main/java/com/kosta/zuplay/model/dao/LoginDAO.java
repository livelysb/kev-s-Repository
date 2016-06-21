package com.kosta.zuplay.model.dao;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginDAO {
	boolean firstLoginCheck(String naverId);
	boolean joinMember(PlayerDTO playerDTO);
}
