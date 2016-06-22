package com.kosta.zuplay.model.service;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface LoginService {
	/**
	 * 理쒖큹 濡쒓렇�씤�뿬遺� �뙆�븙
	 */
	boolean firstLoginCheck(String naverId);
	
	/**
	 * 理쒖큹 濡쒓렇�씤 �씪 �떆�뿉 �젙蹂� DB���옣
	 */
	boolean joinMember(PlayerDTO playerDTO);
	
	/**
	 * 理쒖큹 濡쒓렇�씤 �씪 �떆 �땳�꽕�엫 吏��젙
	 */
}
