package com.kosta.zuplay.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;

@Service
public class PlayerInfoImpl implements PlayerInfo {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public PlayerDTO getPlayer(String playerNickname) {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		return playerInfoDAO.getPlayer(playerNickname);
	}

	@Override
	public List<String> getAllPlayerNickName() {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		return playerInfoDAO.getAllPlayerNickName();
	}

}