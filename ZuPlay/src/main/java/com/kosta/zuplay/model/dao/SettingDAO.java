package com.kosta.zuplay.model.dao;

import com.kosta.zuplay.model.dto.player.SettingDTO;

public interface SettingDAO {
	int settingSave(SettingDTO dto);
	int settingReset(String playerNickname);
	SettingDTO settingSelect(String playerNickname);
	int settingInsert(String playerNickname);
}
