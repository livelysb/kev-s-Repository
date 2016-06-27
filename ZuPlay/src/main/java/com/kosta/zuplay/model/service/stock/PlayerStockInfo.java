package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;

public interface PlayerStockInfo {	
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname);
	
	/**
	 * 플레이어가 가진 한 주식의 수량을 가져오기
	 * */
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd);
	
	/**
	 * 플레이어가 가진 한 주식의 수량을 수정하기
	 * */
	public boolean setPlayerStock(String playerNickname, String isuCd, int plQuantity);
	
	/**
	 * 플레이어의 사이버머니 수정하기
	 * */
	public boolean setPlayerMoney(String playerNickname, int playerMoney);
	
	/**
	 * 주식 거래 히스토리 쓰기
	 * */
	public boolean WriteStockHistory(String playerNickname, String isuCd, int plQuantity, int price, String bs);
	
	
	/**
	 * 주식 거래 히스토리 가져오기
	 * */
	public List<StockDealHistoryDTO> getStockHistory(String playerNickname);
	
	/**
	 * 주식을 포함한 현재 자산 구하기
	 * */
	public int getTotalMoney(String playerNickname);
	
	
}
