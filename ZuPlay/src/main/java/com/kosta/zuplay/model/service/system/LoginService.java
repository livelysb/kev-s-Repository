package com.kosta.zuplay.model.service.system;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginService {
	/**
	 * 최초 로그인여부 파악 + 루비주기
	 */
	boolean firstLoginCheck(String naverId);
	
	/**
	 * 최초 로그인 일 시에 정보 DB저장 + 루비주기
	 */
	boolean joinMember(PlayerDTO playerDTO);
	
	/**
	 * 닉네임 중복 체크
	 */
	boolean checkRepetition(String playerNickname);
	
	/**
	 * 닉네임 가져오기
	 */
	String getNickname(String playerNaverId);
	
	/**
	 * 최종 접속일을 확인하여 금일 첫 방문할 경우 1000 루비 지급
	 * */
	boolean getRubyPerDay(String playerNickname);
}
