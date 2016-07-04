package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.dto.item.ItemMarketDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface ItemAuctionDAO {
	/**
	 * 아이템 검색(모든클래스) throws Exception
	 */
	List<ItemMarketDTO> auctionSearchAll(Map<String, String> map) throws Exception;
	/**
	 * 아이템 검색
	 */
	List<ItemMarketDTO> auctionSearch(Map<String, String> map) throws Exception;

	// 구매시작
	/**
	 * 경매장 등록 가격 가져오기
	 */
	int auctionHowPrice(int imSq) throws Exception;

	/**
	 * 경매장의 해당 아이템 정보 가져오기
	 */
	ItemMarketDTO bringItemInfoByImSq(int imSq) throws Exception;

	/**
	 * 경매 종료물품으로 변경
	 */
	int auctionBuyFinish(int imSq) throws Exception;

	// 구매끝
	// 판매시작
	/**
	 * item_market 테이블에 레코드 삽입
	 */
	int auctionInsertItemMarket(Map<String, String> map) throws Exception;

	// 판매 끝
	/**
	 * 경매 취소 item_market 테이블 해당 레코드 IM_AUCTION_END 컬럼 수정 (T -> X) throws Exception
	 */
	int auctionCancel(int imSq) throws Exception;

	/**
	 * 경매장 유찰품/골드 수령 시작
	 * IM_AUCTION_END  진행중 여부 (T=진행중/F=종료/X=유찰) throws Exception 가져오기
	 */
	String auctionBring(int imSq) throws Exception;
	/**
	 * 내 경매 물품 가져오기
	 */
	List<ItemMarketDTO> auctionMyPage(String playerNickname) throws Exception;
	
	/**
	 * 아이템 등록일자 가져오기
	 */
	List<ItemMarketDTO> auctionSelectBidTime() throws Exception;
	/**
	 * 아이템 목록 업데이트
	 */
	int auctionUpdate(int imSq) throws Exception;
	
	/**
	 * 종료물품 삭제
	 */
	int auctionDeleteFin(int imSq) throws Exception;
}
