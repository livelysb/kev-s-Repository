package com.kosta.zuplay.model.dao;

import java.util.List;

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
	 * INSERT INTO DAILY_PRICE ON PM 3:30
	 * */
	void insertDailyPrice(PriceDTO priceDTO);

}
