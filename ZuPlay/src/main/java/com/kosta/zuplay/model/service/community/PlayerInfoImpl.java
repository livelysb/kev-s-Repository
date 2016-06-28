package com.kosta.zuplay.model.service.community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.stock.EarningRate;

@Service
public class PlayerInfoImpl implements PlayerInfo {

	@Autowired
	private EarningRate earningRate;
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 플레이어 정보 가져오기
	 * */
	@Override
	public PlayerDTO getPlayer(String playerNickname) {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerDTO playerDTO = playerInfoDAO.getPlayer(playerNickname);
		playerDTO.setEarningRate(earningRate.calEarningRate(playerNickname)); //전체 수익률
		return playerDTO;
	}

	/**
	 * 모픈 플레이어의 닉네임 가져오기
	 * */
	@Override
	public List<String> getAllPlayerNickName() {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		return playerInfoDAO.getAllPlayerNickName();
	}

}