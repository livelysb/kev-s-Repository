package com.kosta.zuplay.model.dao.stock;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockInfoDAO {

	/*
	 * 모든 종목 정보 가져오기
	 * */
	List<ListsDTO> getLists();
	
	/**
	 * 모든 주식의 PRICE 가져오기
	 * */
	List<PriceDTO> getPrices();
	
	/**
	 * 특정 주식의 PRICE 가져오기
	 * */
	PriceDTO getPrice(String isuCd);
	
	/**
	 * DB의 페이지 별 주식리스트 보여주기
	 * */
	List<MasterDTO> getStockList(Map<String, Integer> map);
	
}
