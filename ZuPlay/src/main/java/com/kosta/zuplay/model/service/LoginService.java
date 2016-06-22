package com.kosta.zuplay.model.service;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginService {
	/**
	 * 최초 로그인여부 파악
	 */
	boolean firstLoginCheck(String naverId);
	
	/**
	 * 최초 로그인 일 시에 정보 DB저장
	 */
	boolean joinMember(PlayerDTO playerDTO);
	
}
