package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdateDAO {

	void mergePrice(PriceDTO priceDTO);
	
	List<ListsDTO> getLists();
	
	void insertTrdPrc(PriceDTO priceDTO);
	
	void mergeMaster(MasterDTO masterDTO);
	
	List<PriceDTO> getPrice();
	
	void insertDailyPrice(PriceDTO priceDTO);

}
