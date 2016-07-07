package com.kosta.zuplay.model.service.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.stock.PlayerStockDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.service.player.EarningRateService;

@Service
public class PlayerStockServiceImpl implements PlayerStockService {

	@Autowired
	private StockInfoService stockInfoService;

	@Autowired
	private EarningRateService earningRateService;

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) throws Exception {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		return playerStockDAO.getPlayerStocks(playerNickname);
	}

	@Override
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd) throws Exception {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		return playerStockDAO.getPlayerStock(map);
	}

	@Override
	public boolean setPlayerStock(String playerNickname, String isuCd, int plQuantity) throws Exception {
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		map.put("plQuantity", Integer.toString(plQuantity));
		if (playerStockDAO.setPlayerStock(map) > 0)
			return true;
		return false;
	}

	@Override
	public List<MasterDTO> getPlayerStocksDetail(String playerNickname) throws Exception {
		List<MasterDTO> masterList = new ArrayList<MasterDTO>();
		List<PlayerListsDTO> playerStockList = getPlayerStocks(playerNickname);
		for (PlayerListsDTO playerLists : playerStockList) {
			MasterDTO masterDTO = stockInfoService.getStockDetail(playerNickname, playerLists.getIsuCd());
			if (masterDTO.getPlQuantity() != 0) {
				masterDTO.setEarningRate(earningRateService.calItemEarningRate(playerNickname, playerLists.getIsuCd()));
				masterList.add(masterDTO);
			}
		}
		return masterList;
	}

}
