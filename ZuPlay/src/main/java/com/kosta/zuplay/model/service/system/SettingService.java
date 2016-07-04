package com.kosta.zuplay.model.service.system;

import com.kosta.zuplay.model.dto.player.SettingDTO;

public interface SettingService {
	/**
	 * 세팅 저장
	 */
	boolean settingSave(SettingDTO dto) throws Exception;
	/**
	 * 초기화
	 */
	boolean settingReset(String playerNickname) throws Exception;
	/**
	 * 불러오기
	 */
	SettingDTO settingSelect(String playerNickname) throws Exception;
}
