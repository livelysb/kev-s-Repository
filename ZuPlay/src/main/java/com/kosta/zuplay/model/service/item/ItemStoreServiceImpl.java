package com.kosta.zuplay.model.service.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.dao.ItemStoreDAO;
import com.kosta.zuplay.model.dao.LoginDAO;
import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dao.PlayerItemDAO;
import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;
import com.kosta.zuplay.model.service.system.UtilService;

@Service
public class ItemStoreServiceImpl implements ItemStoreService {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private UtilService utilServiceImpl;

	@Override
	public List<ItemDTO> itemStoreSelect(String playerNickname, String itemClass, int page) throws Exception {
		ItemStoreDAO itemStoreDAO = sqlSession.getMapper(ItemStoreDAO.class);
		LoginDAO loginDAO = sqlSession.getMapper(LoginDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		String gender = null;
		if (loginDAO.getGender(playerNickname).equals("M")) {
			gender = "F";
		} else {
			gender = "M";
		}
		map.put("playerGender", gender);
		map.put("itemClass", itemClass);
		map.put("startNo", 1 + ((page - 1) * 8) + "");
		map.put("endNo", page * 8 + "");
		System.out.println(map);
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		if (itemClass.equals("all")) {
			list.clear();
			list = itemStoreDAO.itemStoreSelectAll(map);
		} else {
			list = itemStoreDAO.itemStoreSelect(map);
		}
		System.out.println("[ LOG ] list = " + list + " / list.size() = " + list.size());
		return list;
	}

	/**
	 * 아이템 구매 리턴: 1=정상 / 2=인벤토리 풀 / 3=루비부족 / 4=구매할 수 없는 품목
	 */
	@Override
	@Transactional
	public int itemStoreBuy(String playerNickname, ItemDTO itemDTO, int quantity) throws Exception {
		ItemStoreDAO itemStoreDAO = sqlSession.getMapper(ItemStoreDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		Map<String, String> payRubyMap = new HashMap<String, String>();
		Map<String, String> itemBuyMap = new HashMap<String, String>();
		int ruby = playerInfoDAO.getRuby(playerNickname);
		int price = itemStoreDAO.getPrice(itemDTO.getItemCode());
		if (ruby >= price) {
			int piIndex = utilServiceImpl.indexSearch(playerNickname);
			if (piIndex != 0) {
				payRubyMap.put("playerNickname", playerNickname);
				payRubyMap.put("updateRuby", ruby - price + "");
				playerInfoDAO.updateRuby(payRubyMap);
				itemDTO=itemStoreDAO.itemInfo(itemDTO.getItemCode());
				if (itemDTO.getItemClass().equals("randomBox")) {
					int ranNum = (int) (Math.random() * 10 + 1);
					Map<String, String> randomBoxMap = new HashMap<>();
					String gender = playerInfoDAO.getPlayer(playerNickname).getPlayerGender();
					if (gender.equals("M")) {
						randomBoxMap.put("playerGender", "F");
						if (ranNum <= 1) {
							randomBoxMap.put("randomResult", "uniq");
						} else if (ranNum <= 4) {
							randomBoxMap.put("randomResult", "rare");
						} else {
							randomBoxMap.put("randomResult", "common");
						}
					} else {
						randomBoxMap.put("playerGender", "M");
						if (ranNum <= 1) {
							randomBoxMap.put("randomResult", "uniq");
						} else if (ranNum <= 4) {
							randomBoxMap.put("randomResult", "rare");
						} else {
							randomBoxMap.put("randomResult", "common");
						}
					}
					List<ItemDTO> randomList = itemStoreDAO.itemStoreRandomBoxList(randomBoxMap);
					int randomSize = randomList.size();
					int randomNum = (int) (Math.random() * (randomSize - 1) + 1);

					itemBuyMap.put("playerNickname", playerNickname);
					itemBuyMap.put("itemCode", randomList.get(randomNum).getItemCode());
					itemBuyMap.put("piIndex", piIndex + "");
				} else {
					if (itemDTO.getItemGrade().equals("common")) {
						itemBuyMap.put("playerNickname", playerNickname);
						itemBuyMap.put("itemCode", itemDTO.getItemCode());
						itemBuyMap.put("piIndex", piIndex + "");
					}else{
						return 4;
					}
				}
				playerItemDAO.itemStoreBuy(itemBuyMap);
			} else {
				return 2;
			}
		} else {
			return 3;
		}
		return 1;
	}

	@Override
	@Transactional
	public boolean itemStoreSell(String playerNickname, int piSq, String itemCode) throws Exception {
		ItemStoreDAO itemStoreDAO = sqlSession.getMapper(ItemStoreDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		int ruby = playerInfoDAO.getRuby(playerNickname);
		int price = itemStoreDAO.getPrice(itemCode);
		map.put("playerNickname", playerNickname);
		map.put("updateRuby", ruby - price + "");
		playerInfoDAO.updateRuby(map);
		int result = playerItemDAO.itemDelete(piSq);
		if (result == 0) {
			return false;
		}
		return true;

	}

}
