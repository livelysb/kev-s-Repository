package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdateService {

	/**
	 * 실시간으로 PRICE TABLE 업데이트
	 */
	void stockPriceUpdate() throws Exception;
	
	/**
	 * isuSrtCd를 통해 주식오픈API에서 새로운 값 가져오기
	 */
	PriceDTO getPriceFromAPI(String isuSrtCd) throws Exception;
	
	/**
	 * DB로부터 PRICE 받아와 REALTIME_PRICE TABLE 에 삽입
	 */
	void realtimePriceInsert() throws Exception;
	
	/**
	 * DB의 REALTIME_PRICE TABLE 초기화
	 * */
	void realtimePriceReset() throws Exception;
	
	/**
	 * MASTER TABLE 업데이트
	 * */
	void masterUpdate() throws Exception;
	
	/**
	 * API로부터 모든 MASTER 정보 받아오기
	 * */
	MasterDTO getMasterFromAPI(String isuSrtCd) throws Exception;
	
	/**
	 * DAILY_PRICE 테이블에 체결가(종가) 삽입
	 * */
	void dailyPriceInsert() throws Exception;
	
	/**
	 * masterDTO에서 분류나눠서 리턴해주기
	 * */
	MasterDTO masterMatchKind(MasterDTO masterDTO) throws Exception;

}
