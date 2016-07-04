package com.kosta.zuplay.model.service.system;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.SettingDAO;
import com.kosta.zuplay.model.dto.player.SettingDTO;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean settingSave(SettingDTO dto) throws Exception {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		int result = settingDAO.settingSave(dto);
		if (result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean settingReset(String playerNickname) throws Exception {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		int result = settingDAO.settingReset(playerNickname);
		if (result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public SettingDTO settingSelect(String playerNickname) throws Exception {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		SettingDTO dto=settingDAO.settingSelect(playerNickname);
		System.out.println(dto);
		return dto;
	}

}
