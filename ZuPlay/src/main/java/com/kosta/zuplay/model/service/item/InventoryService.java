package com.kosta.zuplay.model.service.item;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface InventoryService {
	/**
	 * 플레이어의 모든 아이템 가져오기
	 * */
	List<PlayerItemDTO> playerItemSelectAll(String playerNickname) throws Exception;
	
	/**
	 * 플레이어의 아이템 정보 저장
	 * */
	boolean playerItemInsert(List<PlayerItemDTO> list) throws Exception;
	
	/**
	 * 플레이어가 입고있는 옷 정보 가져오기
	 * */
	List<PlayerItemDTO> playerItemWorn(String playerNickname) throws Exception;
}
