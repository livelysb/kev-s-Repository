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
import com.kosta.zuplay.model.service.PlayerInfo;
import com.kosta.zuplay.model.service.UtilService;

@Service
public class ItemStoreServiceImpl implements ItemStoreService {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private UtilService utilServiceImpl;

	@Override
	public List<ItemDTO> itemStoreSelect(String playerNickname, String itemClass, int page) {
		ItemStoreDAO itemStoreDAO = sqlSession.getMapper(ItemStoreDAO.class);
		LoginDAO loginDAO = sqlSession.getMapper(LoginDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		String gender=null;
		if(loginDAO.getGender(playerNickname).equals("M")){
			gender="F";
		}else{
			gender="M";
		}
		map.put("playerGender", gender);
		map.put("itemClass", itemClass);
		map.put("startNo", 1 + ((page - 1) * 8) + "");
		map.put("endNo", page * 8 + "");
		System.out.println(map);
		List<ItemDTO> list = new ArrayList<ItemDTO>();
			if(itemClass.equals("all")){
				list=itemStoreDAO.itemStoreSelectAll(map);
			}else{
				list=itemStoreDAO.itemStoreSelect(map);
			}
		System.out.println("[ LOG ] list = " + list+" / list.size() = "+list.size());
		return list;
	}

	/**
	 * 아이템 구매 리턴: 1=정상 / 2=인벤토리 풀 / 3=루비부족
	 */
	@Override
	@Transactional
	public int itemStoreBuy(String playerNickname, ItemDTO itemDTO, int quantity) {
		ItemStoreDAO itemStoreDAO = sqlSession.getMapper(ItemStoreDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		Map<String, String> payRubyMap = new HashMap<String, String>();
		Map<String, String> itemBuyMap = new HashMap<String, String>();
		int ruby = playerInfoDAO.getRuby(playerNickname);
		System.out.println("[ LOG ] : " + playerNickname + " 님의 루비 = " + ruby);
		int price = itemStoreDAO.getPrice(itemDTO.getItemCode());
		System.out.println("[ LOG ] : " + itemDTO.getItemCode() + " 아이템의 가격 = " + price);
		if (ruby >= price) {
			int piIndex = utilServiceImpl.indexSearch(playerNickname);
			System.out.println("[ LOG ] : " + playerNickname + " 님의 인벤토리 빈 인덱스 = " + piIndex);
			if (piIndex != 0) {
				payRubyMap.put("playerNickname", playerNickname);
				payRubyMap.put("updateRuby", ruby-price + "");
				System.out.println();
				int payRubyResult = playerInfoDAO.updateRuby(payRubyMap);
				System.out.println(payRubyResult + " 개 행 수정(1개 = 정상실행)");
				itemBuyMap.put("playerNickname", playerNickname);
				itemBuyMap.put("itemCode", itemDTO.getItemCode());
				itemBuyMap.put("piIndex", piIndex + "");
				int itembuyResult = playerItemDAO.itemStoreBuy(itemBuyMap);
				System.out.println(itembuyResult + " 개 행 수정(1개 = 정상실행)");
			} else {
				System.out.println("[ LOG ] : 인벤토리 가득 참");
				// 인벤토리 가득참
				return 2;
			}
		} else {
			System.out.println("[ LOG ] : 루비부족");
			// 루비부족
			return 3;
		}
		return 1;
	}

	@Override
	@Transactional
	public boolean itemStoreSell(String playerNickname, int piSq, String itemCode) {
		ItemStoreDAO itemStoreDAO = sqlSession.getMapper(ItemStoreDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		int price = itemStoreDAO.getPrice(itemCode);
		map.put("playerNickname", playerNickname);
		map.put("price", -price + "");
		playerInfoDAO.updateRuby(map);
		int result = playerItemDAO.itemDelete(piSq);
		if (result == 0) {
			return false;
		}
		return true;

	}

}
