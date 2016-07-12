package com.kosta.zuplay.model.service.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.kosta.zuplay.model.dao.ItemAuctionDAO;
import com.kosta.zuplay.model.dao.ItemStoreDAO;
import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dao.PlayerItemDAO;
import com.kosta.zuplay.model.dto.item.ItemMarketDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.system.UtilService;
import com.kosta.zuplay.util.vo.PlayerVO;

@Service
public class ItemAuctionServiceImpl implements ItemAuctionService {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private UtilService utilServiceImpl;
	@Autowired
	private ServletContext application;

	/**
	 * search item in item auction
	 * 
	 * @param keyword
	 *            : What you want to search
	 * @param itemClass
	 *            : If you want to search for some parts
	 * @param page
	 *            : eight units in one page
	 */
	@Override
	public List<ItemMarketDTO> auctionSearch(String playerNickname, String keyword, String itemClass, int page)
			throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		List<ItemMarketDTO> list = null;
		Map<String, String> map = new HashMap<String, String>();
		String pGender = playerInfoDAO.getPlayer(playerNickname).getPlayerGender();
		String gender = null;
		if (pGender.equals("M")) {
			gender = "f";
		} else {
			gender = "m";
		}
		map.put("keyword", keyword);
		map.put("itemClass", itemClass);
		map.put("startNo", 1 + ((page - 1) * 10) + "");
		map.put("endNo", page * 10 + "");
		map.put("gender", gender);
		if (itemClass.equals("all")) {
			list = itemAuctionDAO.auctionSearchAll(map);
		} else {
			list = itemAuctionDAO.auctionSearch(map);
		}
		return list;
	}

	/**
	 * buy item in item auction return int(1=success/2=not enough
	 * inventory/3=not enough Ruby/4=sold out item)
	 */
	@Override
	@Transactional
	public int auctionBuy(String playerNickname, int imSq) throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		Map<String, String> payRubyMap = new HashMap<String, String>();
		int ruby = playerInfoDAO.getRuby(playerNickname); // 보유루비 확인
		System.out.println("[ LOG ] : " + playerNickname + " 님의 루비 = " + ruby);
		int price = itemAuctionDAO.auctionHowPrice(imSq);
		if (ruby >= price) {
			int piIndex = utilServiceImpl.indexSearch(playerNickname);
			System.out.println("[ LOG ] : " + playerNickname + " 님의 인벤토리 빈 인덱스 = " + piIndex);
			if (piIndex != 0) {
				payRubyMap.put("playerNickname", playerNickname);
				payRubyMap.put("updateRuby", ruby - price + "");
				int payRubyResult = playerInfoDAO.updateRuby(payRubyMap);
				if (payRubyResult != 0) {
					ItemMarketDTO itemMarketDTO = itemAuctionDAO.bringItemInfoByImSq(imSq);
					int insertResult = playerItemDAO.auctionInsertPlayerItem(new PlayerItemDTO(0, playerNickname,
							itemMarketDTO.getItemCode(), piIndex, itemMarketDTO.getItemDTO()));
					if (insertResult != 0) {
						itemAuctionDAO.auctionBuyFinish(imSq);
					} else {
						return 4;
					}
				} else {
					return 4;
				}
			} else {
				return 2;
			}
		} else {
			return 3;
		}
		try {
			ItemMarketDTO itemMarketDTO = itemAuctionDAO.bringItemInfoByImSq(imSq);
			String sellerNickname = itemMarketDTO.getPlayerNickname();
			PlayerVO pv = (PlayerVO) application.getAttribute("#"+sellerNickname);
			if (pv != null) {
				WebSocketSession webSession = pv.getSession();
				String json = "{\"type\":\"notiAuctionEndBySeller\",\"data\":\"" + itemMarketDTO + "\"}";
				webSession.sendMessage(new TextMessage(json));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * sell item in item auction return boolean(true=success/fail=fail)
	 * 
	 */
	@Override
	@Transactional
	public boolean auctionSell(String playerNickname, int piSq, int imPurchasePrice) throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		PlayerItemDTO playerItemDTO = playerItemDAO.bringItemInfoByPiSq(piSq);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("imPurchasePrice", imPurchasePrice + "");
		map.put("itemCode", playerItemDTO.getItemCode());
		int insertResult = itemAuctionDAO.auctionInsertItemMarket(map);
		if (insertResult != 0) {
			int deleteResult = playerItemDAO.itemDelete(piSq);
			if (deleteResult == 0) {
				System.out.println("[ LOG ] : player_item 레코드 삭제 실패하였습니다.");
				return false;
			}
		} else {
			System.out.println("[ LOG ] : 경매장에 아이템 등록을 실패하였습니다.");
			return false;
		}
		return true;
	}

	/**
	 * 경매장 경매 취소
	 */
	@Override
	public boolean auctionCancel(int imSq) throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		int result = itemAuctionDAO.auctionCancel(imSq);
		if (result == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 경매장 유찰품/골드 수령 IM_AUCTION_END 진행중 여부 (T=진행중/F=종료/X=유찰)
	 */
	@Override
	@Transactional
	public boolean auctionBring(String playerNickname, int imSq) throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		String imAuctionEnd = itemAuctionDAO.auctionBring(imSq);
		if (imAuctionEnd.equals("F")) {
			System.out.println(imAuctionEnd);
			Map<String, String> map = new HashMap<String, String>();
			int ruby = playerInfoDAO.getRuby(playerNickname);
			int price = itemAuctionDAO.auctionHowPrice(imSq);
			itemAuctionDAO.auctionDeleteFin(imSq);

			map.put("playerNickname", playerNickname);
			map.put("updateRuby", ruby + price + "");
			int result = playerInfoDAO.updateRuby(map);
			System.out.println(map);
			if (result == 0) {
				return false;
			}
		} else if (imAuctionEnd.equals("X")) {
			ItemMarketDTO itemMarketDTO = itemAuctionDAO.bringItemInfoByImSq(imSq);
			int piIndex = utilServiceImpl.indexSearch(playerNickname);
			if (piIndex != 0) {
				playerItemDAO.auctionInsertPlayerItem(new PlayerItemDTO(0, playerNickname, itemMarketDTO.getItemCode(),
						piIndex, itemMarketDTO.getItemDTO()));
				itemAuctionDAO.auctionDeleteFin(imSq);
			} else {
				return false;
			}
		}
		System.out.println("성공");
		return true;
	}

	/**
	 * 내 경매 물품 가져오기
	 */
	@Override
	public List<ItemMarketDTO> auctionMyPage(String playerNickname) throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		return itemAuctionDAO.auctionMyPage(playerNickname);
	}

	@Override
	@Scheduled(cron = "00 00 12 * * *")
	@Transactional
	public void itemAuctionUpdate() throws Exception {
		System.out.println("아이템 경매장 목록 업데이트 시작");
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		List<ItemMarketDTO> list = itemAuctionDAO.auctionSelectBidTime();
		String sysdate = utilServiceImpl.currentDate();
		for (int i = 0; i < list.size(); i++) {
			if (sysdate.equals(list.get(i).getImBidTime())) {
				System.out.println(list.get(i).getImSq());
				int result = itemAuctionDAO.auctionUpdate(list.get(i).getImSq());
				System.out.println(result);

			}
		}
	}

	@Override
	public boolean itemAuctionEndSearch(String playerNickname) throws Exception {
		ItemAuctionDAO itemAuctionDAO = sqlSession.getMapper(ItemAuctionDAO.class);
		List<ItemMarketDTO> list = itemAuctionDAO.itemAuctionEndSearch(playerNickname);
		boolean result = true;
		if (list.size() == 0) {
			result = false;
		}
		return result;
	}

}
