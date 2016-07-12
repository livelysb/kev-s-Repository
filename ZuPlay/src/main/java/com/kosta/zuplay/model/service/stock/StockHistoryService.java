package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.EarningRateHistory;
import com.kosta.zuplay.model.dto.stock.MasterDTO;

public interface StockHistoryService {

	/**
	 * 1. 일일수익률 가져오기
	 * 2. 수익률 Best/Worst 3 주식 뽑아오기
	 * 3. 구매내역 가져오기 (동적쿼리, 페이징 처리)
	 * 
	 * */
	
	/**
	 * 1. 일일수익률 가져오기
	 * */
	List<EarningRateHistory> getEarningRateList(String playerNickname);
	
	
	/**
	 * 2.1. 수익률 Best 3 주식 뽑아오기
	 * */
	List<MasterDTO> getBest3(String playerNickname);
	
	/**
	 * 2.2. 수익률 Worst 3 주식 뽑아오기
	 * */
	List<MasterDTO> getWorst3(String playerNickname);
	
	/**
	 * 3. 구매내역 가져오기
	 * */
	List<MasterDTO> getStockDealHistory(String playerNickname, String orderBy, boolean asc);
	
}
