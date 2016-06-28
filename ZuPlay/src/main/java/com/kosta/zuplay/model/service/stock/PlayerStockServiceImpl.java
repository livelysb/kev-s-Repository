package com.kosta.zuplay.model.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.dao.stock.PlayerStockDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

@Service
public class PlayerStockServiceImpl implements PlayerStockService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		return playerStockDAO.getPlayerStocks(playerNickname);
	}

	@Override
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd) {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		return playerStockDAO.getPlayerStock(map);
	}

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
