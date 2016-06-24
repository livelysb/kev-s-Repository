package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface GetStockInfo {
	
	/**
	 * DB의 모든 isuSerCd 가져오기
	 */
	public List<PriceDTO> getPrice();
	
	/**
	 * DB의 모든 price 가져오기
	 */
	public List<ListsDTO> getLists();
	
	/**
	 * DB의 모든 주식리스트 보여주기
	 * */
	public List<MasterDTO> getStockList(int page);
	
	/**
	 * 기업의 상세정보 보여주기
	 * */
	public MasterDTO getStockDetail(String isuCd);
	
}
