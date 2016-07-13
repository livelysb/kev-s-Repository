package com.kosta.zuplay.model.service.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.FriendDAO;
import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dao.SettingDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.service.item.InventoryService;
import com.kosta.zuplay.model.service.stock.PlayerStockService;
import com.kosta.zuplay.model.service.stock.StockInfoService;
import com.kosta.zuplay.model.service.system.UtilService;

@Service
public class PlayerInfoServicempl implements PlayerInfoService {

	@Autowired
	private EarningRateService earningRateService;

	@Autowired
	private PlayerInfoService playerInfoService;

	@Autowired
	private PlayerStockService playerStockService;

	@Autowired
	private StockInfoService stockInfoService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private UtilService utilService;

	@Override
	public PlayerDTO getPlayer(String playerNickname) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerDTO playerDTO = playerInfoDAO.getPlayer(playerNickname);
		return playerDTO;
	}

	@Override
	public PlayerDTO getPlayerDetail(String playerNickname) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerDTO playerDTO = getPlayer(playerNickname);
		playerDTO.setPlayerItemDTO(inventoryService.playerItemSelectAll(playerNickname));
		playerDTO.setLikerList(playerInfoDAO.getLikeList(playerNickname));// 좋아하는사람
																			// 목록
		playerDTO.setEarningRate(earningRateService.calDailyEarningRate(playerNickname)); // 일일수익률
		playerDTO.setTotalEarningRate(earningRateService.calEarningRate(playerNickname)); // 전체수익률
		playerDTO.setTotalMoney(getTotalMoney(playerNickname)); // 총 자산
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
	public int getTotalMoney(String playerNickname) throws Exception {
		int currentMoney = playerInfoService.getMoney(playerNickname);
		List<PlayerListsDTO> playerLists = playerStockService.getPlayerStocks(playerNickname);
		for (PlayerListsDTO playerListsDTO : playerLists) {
			currentMoney += stockInfoService.getPrice(playerListsDTO.getIsuCd()).getTrdPrc()
					* playerListsDTO.getPlQuantity();
		}
		return currentMoney;
	}

	@Override
	public Boolean getRubyPerDay(String playerNickname) throws Exception {
		boolean result = false;
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerDTO playerDTO = playerInfoDAO.getPlayer(playerNickname);
		String lastAccess = playerDTO.getPlayerLastAccess().replaceAll("-", "");
		if (!utilService.currentDate().equals(lastAccess.substring(0, 8))) {
			System.out.println(playerDTO.getPlayerNickname() + "님, 오늘의 최초접속 5000원 드립니다.");
			// 루비 5000원 추가
			playerDTO.setPlayerRuby(playerDTO.getPlayerRuby() + 5000);
			playerInfoService.updateRuby(playerDTO.getPlayerNickname(), playerDTO.getPlayerRuby());
			playerDTO.setTodayFirst(true);// 첫 접속인 경우 뷰에 알리기 위함;
			result = true;
		}
		playerInfoDAO.lastAccessUpdate(playerNickname);// 최종접속일 갱신
		return result;
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
	public List<PlayerDTO> playerInfoSelectAll(String playerNickname, String keyword) throws Exception {
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		List<String> friendList = friendDAO.friendSelectOnlyNicknameA(playerNickname);
		List<String> friendListTemp = friendDAO.friendSelectOnlyNicknameB(playerNickname);
		for (int i = 0; i < friendListTemp.size(); i++) {
			friendList.add(friendListTemp.get(i));
		}
		List<PlayerDTO> list = null;
		try {
			list = playerInfoDAO.playerInfoSelectAll(keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = list.size()-1 ; i>=0;i--){
			if(friendList.contains(list.get(i).getPlayerNickname())){
				list.remove(i);
			}
		}
		System.out.println(list);
		return list;
	}

}
