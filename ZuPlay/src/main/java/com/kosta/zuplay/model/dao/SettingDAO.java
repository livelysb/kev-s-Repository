package com.kosta.zuplay.model.dao;

import com.kosta.zuplay.model.dto.player.SettingDTO;

public interface SettingDAO {
	int settingSave(SettingDTO dto) throws Exception;
	int settingReset(String playerNickname) throws Exception;
	SettingDTO settingSelect(String playerNickname) throws Exception;
	int settingInsert(String playerNickname) throws Exception;
}
