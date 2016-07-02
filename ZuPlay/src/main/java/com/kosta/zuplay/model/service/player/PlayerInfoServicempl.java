package com.kosta.zuplay.model.service.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.service.stock.PlayerStockService;
import com.kosta.zuplay.model.service.stock.StockInfoService;

@Service
public class PlayerInfoServicempl implements PlayerInfoService {

	@Autowired
	private EarningRateService earningRate;

	@Autowired
	private PlayerInfoService playerInfoService;

	@Autowired
	private PlayerStockService playerStockService;

	@Autowired
	private StockInfoService stockInfoService;

	@Autowired
	private SqlSession sqlSession;

	@Override
	public PlayerDTO getPlayer(String playerNickname) throws Exception{
		System.out.println("플레이어 닉네임 : " + playerNickname);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerDTO playerDTO = playerInfoDAO.getPlayer(playerNickname);
		System.out.println(playerDTO.getPlayerLastAccess());
		playerDTO.setEarningRate(earningRate.calEarningRate(playerNickname)); // 전체
																				// 수익률
		System.out.println(playerDTO.getEarningRate());
		return playerDTO;
	}

	@Override
	public List<String> getAllPlayerNickName() throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		return playerInfoDAO.getAllPlayerNickName();
	}

	@Override
	public boolean setPlayerMoney(String playerNickname, int playerMoney) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("playerMoney", Integer.toString(playerMoney));
		if (playerInfoDAO.setPlayerMoney(map) > 0)
			return true;
		return false;
	}

	@Override
	public int getTotalMoney(String playerNickname)  throws Exception{
		int currentMoney = playerInfoService.getMoney(playerNickname);
		List<PlayerListsDTO> playerLists = playerStockService.getPlayerStocks(playerNickname);
		for (PlayerListsDTO playerListsDTO : playerLists) {
			currentMoney += stockInfoService.getPrice(playerListsDTO.getIsuCd()).getTrdPrc()
					* playerListsDTO.getPlQuantity();
		}
		return currentMoney;
	}
	@Override
	public int getMoney(String playerNickname) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		return playerInfoDAO.getMoney(playerNickname);
	}
	
	@Override
	public int getRuby(String playerNickname) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		return playerInfoDAO.getRuby(playerNickname);
	}

	@Override
	public boolean updateRuby(String playerNickname, int ruby) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);

		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("updateRuby", ruby + "");
		int result = playerInfoDAO.updateRuby(map);
		if (result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<PlayerDTO> playerInfoSelectAll(String keyword) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		List<PlayerDTO> list=null;
		try{
			list = playerInfoDAO.playerInfoSelectAll(keyword);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

}
