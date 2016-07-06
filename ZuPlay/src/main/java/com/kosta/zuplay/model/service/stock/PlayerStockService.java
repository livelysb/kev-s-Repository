package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;

public interface PlayerStockService {	
	
	/**
	 * 플레이어가 가지는 주식정보 가져오기
	 * */
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) throws Exception;
	
	/**
	 * 플레이어가 가진 한 주식의 수량만을 가져오기
	 * */
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd) throws Exception;
	
	/**
	 * 플레이어가 가진 한 주식의 수량을 수정하기
	 * */
	public boolean setPlayerStock(String playerNickname, String isuCd, int plQuantity) throws Exception;
	
	/**
	 * 플레이어가 가지는 주식 세부정보 가져오기
	 * */
	public List<MasterDTO> getPlayerStocksDetail(String playerNickname) throws Exception;
}
