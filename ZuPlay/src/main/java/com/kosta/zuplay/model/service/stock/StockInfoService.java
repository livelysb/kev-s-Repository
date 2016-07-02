package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockInfoService {
	
	/**
	 * DB의 모든 prices 가져오기
	 */
	public List<PriceDTO> getPrices();
	
	/**
	 * DB의 한 기업의 price 가져오기
	 * */
	public PriceDTO getPrice(String isuCd);
	
	/**
	 * DB의 모든 리스트정보 가져오기
	 */
	public List<ListsDTO> getLists();
	
	/**
	 * DB의 모든 주식리스트 보여주기
	 * */
	public List<MasterDTO> getStockList(int page, String keyword);
	
	/**
	 * 기업의 상세정보 보여주기
	 * */
	public MasterDTO getStockDetail(String playerNickname, String isuCd);
	
	/**
	 * 해당 종목 관련 개수 구하기
	 * */
	int getListSize(String isuKorAbbrv);
	
}
