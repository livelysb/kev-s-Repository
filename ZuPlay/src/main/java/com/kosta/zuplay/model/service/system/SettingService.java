package com.kosta.zuplay.model.service.system;

import com.kosta.zuplay.model.dto.player.SettingDTO;

public interface SettingService {
	/**
	 * 세팅 저장
	 */
	boolean settingSave(SettingDTO dto);
	/**
	 * 초기화
	 */
	boolean settingReset(String playerNickname);
	/**
	 * 불러오기
	 */
	SettingDTO settingSelect(String playerNickname);
}
