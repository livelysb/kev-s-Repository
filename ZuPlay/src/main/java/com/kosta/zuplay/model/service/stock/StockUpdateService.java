package com.kosta.zuplay.model.service.stock;

import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdateService {

	/**
	 * 실시간으로 PRICE TABLE 업데이트
	 */
	public void stockPriceUpdate();
	
	/**
	 * isuSrtCd를 통해 주식오픈API에서 새로운 값 가져오기
	 */
	public PriceDTO getPriceFromAPI(String isuSrtCd);
	
	/**
	 * DB로부터 PRICE 받아와 REALTIME_PRICE TABLE 에 삽입
	 */
	public void realtimePriceInsert();
	
	/**
	 * DB의 REALTIME_PRICE TABLE 초기화
	 * */
	public void realtimePriceReset();
	
	/**
	 * MASTER TABLE 업데이트
	 * */
	public void masterUpdate();
	
	/**
	 * API로부터 모든 MASTER 정보 받아오기
	 * */
	public MasterDTO getMasterFromAPI(String isuSrtCd);
	
	/**
	 * DAILY_PRICE 테이블에 체결가(종가) 삽입
	 * */
	public void dailyPriceInsert();
	
	
}
