package com.kosta.zuplay.model.DAO;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginDAO {
	/**
	 * 최초로그인 체크
	 */
	boolean firstLoginCheck(String naverId);
	
	/**
	 * 정보저장
	 */
	boolean joinMember(PlayerDTO playerDTO);
}
