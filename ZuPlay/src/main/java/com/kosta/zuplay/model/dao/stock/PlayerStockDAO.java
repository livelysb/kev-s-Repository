package com.kosta.zuplay.model.dao.stock;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

public interface PlayerStockDAO {

	/**
	 * 플레이어의 주식정보 가져오기
	 * */
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) throws Exception;
	
	/**
	 * 플레이어가 가진 한 주식의 수량만을 가져오기
	 * */
	public PlayerListsDTO getPlayerStock(Map<String, String> map) throws Exception;
	
	/**
	 * 플레이어가 가진 한 주식의 수량을 수정하기
	 * */
	public int setPlayerStock(Map<String, String> map) throws Exception;
	
	/**
	 * 사용자가 즐겨찾는 기업코드 가져오기
	 * */
	List<String> getLikeStock(String isuCd) throws Exception;
	
}
