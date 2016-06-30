package com.kosta.zuplay.model.service.system;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.LoginDAO;
import com.kosta.zuplay.model.dao.SettingDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PlayerInfoService playerInfoService;
	
	@Override
	public boolean firstLoginCheck(String naverId) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);	
		PlayerDTO playerDTO=loginDAO.firstLoginCheck(naverId);
		if(playerDTO!=null){
			return false;
		}
		return true;
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		SettingDAO settingDAO=sqlSession.getMapper(SettingDAO.class);
		int result=loginDAO.joinMember(playerDTO);
		settingDAO.settingReset(playerDTO.getPlayerNickname());
		if(result==0){
			return false;
		}
		return true;
	}

	@Override
	public boolean checkRepetition(String playerNickname) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		String playerNaverId=loginDAO.checkRepetiton(playerNickname);
		if(playerNaverId!=null){
			return false;
		}
		return true;
	}

	@Override
	public String getNickname(String playerNaverId) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		return loginDAO.getNickname(playerNaverId);
	}

	@Override
	public boolean getRubyPerDay(String playerNickname) {
		if(playerInfoService.updateRuby(playerNickname,playerInfoService.getRuby(playerNickname) + 1000))
			return true;
		return false;
	}
}
