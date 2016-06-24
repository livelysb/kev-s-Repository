package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdateDAO {
	
	/**
	 * UPDATE PRICE TABLE
	 * */
	void mergePrice(PriceDTO priceDTO);
	
	/*
	 * GET LISTS(기업정보) -> isuSrtCd 가져오기 (API에서 정보 받아오기 위해서)
	 * */
	List<ListsDTO> getLists();
	
	/**
	 * INSERT INTO REALTIME_PRICE TABLE
	 * */
	void insertRealtimePrice(PriceDTO priceDTO);
	
	/**
	 * INITIALIZE REALTIME_PRICE TABLE EVERY MORNING
	 * */
	void resetRealtimePrice();
	
	/**
	 * UPDATE MASTER TABLE EVERY MORNIG
	 * */
	void mergeMaster(MasterDTO masterDTO);
	
	/**
	 * GET ALL STOCK PRICES FROM DB
	 * */
	List<PriceDTO> getPrices();
	
	/**
	 * Get a stock price from DB
	 * */
	PriceDTO getPrice(String isuCd);
	
	
	/**
	 * INSERT INTO DAILY_PRICE 테이블에 3:30분의 체결가(종가) 삽입
	 * */
	void insertDailyPrice(PriceDTO priceDTO);
	
	/**
	 * DB의 페이지 별 주식리스트 보여주기
	 * */
	List<MasterDTO> getStockList(Map<String, Integer> map);
	
	/**
	 * 플레이어의 주식정보 가져오기
	 * */
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname);
	
	/**
	 * 플레이어가 가진 한 주식의 수량만을 가져오기
	 * */
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd);
	
	/**
	 * 플레이어의 일일 수익률 계산해서 insert 하기
	 * */
	public double getEarningRate(String playerNickname);

}
