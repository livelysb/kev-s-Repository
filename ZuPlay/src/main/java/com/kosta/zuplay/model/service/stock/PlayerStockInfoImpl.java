package com.kosta.zuplay.model.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.StockUpdateDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

@Service
public class PlayerStockInfoImpl implements PlayerStockInfo {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	@Override
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		return stockUpdateDAO.getPlayerStocks(playerNickname);
	}

	/**
	 * 플레이어가 가진 한 주식의 수량을 가져오기
	 * */
	@Override
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		return stockUpdateDAO.getPlayerStock(map);
	}

	/**
	 * 플레이어가 가진 한 주식의 수량을 수정하기
	 * */
	@Override
	public boolean setPlayerStock(String playerNickname, String isuCd, int plQuantity) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		map.put("plQuantity", Integer.toString(plQuantity));
		if(stockUpdateDAO.setPlayerStock(map)>0)
			return true;
		return false;
	}

	/**
	 * 플레이어의 사이버머니 수정하기
	 * */
	@Override
	public boolean setPlayerMoney(String playerNickname, int playerMoney) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("playerMoney", Integer.toString(playerMoney));
		if(stockUpdateDAO.setPlayerMoney(map)>0)
			return true;
		return false;
	}

	/**
	 * 주식 거래 히스토리
	 * */
	@Override
	public boolean WriteStockHistory(String playerNickname, String isuCd, int plQuantity, int price, String bs) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		map.put("plQuantity", Integer.toString(plQuantity));
		map.put("price", Integer.toString(price));
		map.put("bs", bs);
		if(stockUpdateDAO.WriteStockHistory(map)>0)
			return true;
		return false;
	}

}
