package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.dto.item.ItemMarketDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface ItemAuctionDAO {
	/**
	 * 아이템 검색
	 */
	List<ItemMarketDTO> auctionSearch(Map<String, String> map);

	// 구매시작
	/**
	 * 경매장 등록 가격 가져오기
	 */
	int auctionHowPrice(String imSq);

	/**
	 * 경매장의 해당 아이템 정보 가져오기
	 */
	ItemDTO bringItemInfoByImSq(String imSq);

	/**
	 * 아이템정보 player_item 테이블에 삽입
	 */
	int auctionInsertPlayerItem(PlayerItemDTO playerItemDTO);

	/**
	 * 경매 종료물품으로 변경
	 */
	int auctionBuyFinish(String imSq);

	// 구매끝
	// 판매시작
	/**
	 * player_item 테이블에서 아이템 정보 가져오기
	 */
	ItemDTO bringItemInfoByPiSq(String piSq);

	/**
	 * item_market 테이블에 레코드 삽입
	 */
	int auctionInsertItemMarket(Map<String, String> map);

	/**
	 * player_item 테이블 레코드 삭제
	 */
	int auctionDeletePlayerItem(String piSq);
	// 판매 끝
	/**
	 * 경매 취소 item_market 테이블 해당 레코드 IM_AUCTION_END 컬럼 수정 (T -> X)
	 */
	int auctionCancle(String imSq);

	/**
	 * 경매장 유찰품/골드 수령 시작
	 * IM_AUCTION_END  진행중 여부 (T=진행중/F=종료/X=유찰) 가져오기
	 */
	String auctionBring(String imSq);
	/**
	 * 내 경매 물품 가져오기
	 */
	List<ItemMarketDTO> auctionMyPage(String playerNickname);
}
