package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface PlayerItemDAO {
	List<PlayerItemDTO> playerItemSelectAll(String playerNickname);
	int playerItemInsert(List<PlayerItemDTO> list);
	/**
	 * 아이템정보 player_item 테이블에 삽입
	 */
	int auctionInsertPlayerItem(PlayerItemDTO playerItemDTO);
	/**
	 * player_item 테이블에서 아이템 정보 가져오기
	 */
	PlayerItemDTO bringItemInfoByPiSq(int piSq);
	/**
	 * player_item 테이블 레코드 삭제
	 */
	int itemDelete(int piSq);
	/**
	 * player_item 테이블에 삽입
	 */
	int itemStoreBuy(Map<String, String> map);
	/**
	 * 인벤토리 빈 인덱스가져오기
	 */
	List<Integer> getItemIndex(String playerNickname);

}
