package com.kosta.zuplay.model.dao.stock;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.stock.DailyPriceDTO;
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;
import com.kosta.zuplay.model.dto.stock.RealTimePriceDTO;

public interface StockInfoDAO {

	/*
	 * 모든 종목 정보 가져오기
	 * */
	List<ListsDTO> getLists() throws Exception;
	
	/**
	 * 모든 주식의 PRICE 가져오기
	 * */
	List<PriceDTO> getPrices() throws Exception;
	
	/**
	 * 특정 주식의 PRICE 가져오기
	 * */
	PriceDTO getPrice(String isuCd) throws Exception;
	
	/**
	 * DB의 페이지 별 주식리스트 보여주기
	 * */
	List<MasterDTO> getStockList(Map<String, String> map) throws Exception;
	
	/**
	 * 해당 종목 관련 개수 구하기
	 * */
	int getListSize(String isuKorAbbrv) throws Exception;
	
	/**
	 * 기업의 상세정보 가져오기
	 * */
	MasterDTO getStock(String isuCd) throws Exception;
	
	/**
	 * 기업의 실시간 주가 가져오기
	 * */
	List<RealTimePriceDTO> getRTPList(String isuCd) throws Exception;
	
	/**
	 * 기업의 한달간 주가 가져오기
	 * */
	List<DailyPriceDTO> getDPList(String isuCd) throws Exception;
	
	/**
	 * 기업의 분류 얻어오기
	 * */
	
	
	
}
