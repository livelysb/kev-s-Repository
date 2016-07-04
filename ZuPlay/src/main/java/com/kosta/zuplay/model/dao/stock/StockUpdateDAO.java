package com.kosta.zuplay.model.dao.stock;

import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdateDAO {
	
	/**
	 * PRICE TABLE 업데이트
	 * */
	void priceUpdate(PriceDTO priceDTO) throws Exception;
	
	/**
	 * REALTIME_PRICE TABLE에 삽입
	 * */
	void realtimePriceInsert(PriceDTO priceDTO) throws Exception;
	
	/**
	 * REALTIME_PRICE TABLE 초기화
	 * */
	void realtimePriceReset() throws Exception;
	
	/**
	 * MASTER TABLE 업데이트
	 * */
	void masterUpdate(MasterDTO masterDTO) throws Exception;
		
	/**
	 * DAILY_PRICE 테이블에 체결가(종가) throws Exception 삽입
	 * */
	void dailyPriceInsert(PriceDTO priceDTO) throws Exception;
	
}
