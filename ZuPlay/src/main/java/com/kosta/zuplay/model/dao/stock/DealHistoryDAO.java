package com.kosta.zuplay.model.dao.stock;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.stock.EarningRateHistoryDTO;
import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;

public interface DealHistoryDAO {

	/**
	 * 주식 거래 히스토리  삽입하기
	 * */
	public int stockHistoryInsert(Map<String, String> map) throws Exception;
	
	
	/**
	 * 플레이어의 구매/판매 기록 가져오기
	 * */
	public List<StockDealHistoryDTO> getStockHistory(String playerNickname) throws Exception;
	
	/**
	 * 플레이어의 일일 수익률 히스토리 가져오기
	 * */
	public List<EarningRateHistoryDTO> getEarningRateHistory(String playerNickname) throws Exception;
}
