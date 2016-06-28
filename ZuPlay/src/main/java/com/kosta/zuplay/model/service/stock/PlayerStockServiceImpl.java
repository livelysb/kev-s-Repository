package com.kosta.zuplay.model.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.stock.PlayerStockDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

@Service
public class PlayerStockServiceImpl implements PlayerStockService {

	@Autowired
	private SqlSession sqlSession;

	
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	@Override
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		return playerStockDAO.getPlayerStocks(playerNickname);
	}

	/**
	 * 플레이어가 가진 한 주식의 수량을 가져오기
	 * */
	@Override
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd) {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		return playerStockDAO.getPlayerStock(map);
	}

	/**
	 * 플레이어가 가진 한 주식의 수량을 수정하기
	 * */
	@Override
	public boolean setPlayerStock(String playerNickname, String isuCd, int plQuantity) {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		map.put("plQuantity", Integer.toString(plQuantity));
		if(playerStockDAO.setPlayerStock(map)>0)
			return true;
		return false;
	}

}
