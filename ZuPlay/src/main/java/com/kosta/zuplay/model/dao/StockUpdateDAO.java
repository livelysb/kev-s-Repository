package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

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
	 * GET ALL PRICE FROM DB
	 * */
	List<PriceDTO> getPrice();
	
	/**
	 * INSERT INTO DAILY_PRICE 테이블에 3:30분의 체결가(종가) 삽입
	 * */
	void insertDailyPrice(PriceDTO priceDTO);
	
	/**
	 * DB의 페이지 별 주식리스트 보여주기
	 * */
	List<MasterDTO> getStockList(Map<String, Integer> map);

}
