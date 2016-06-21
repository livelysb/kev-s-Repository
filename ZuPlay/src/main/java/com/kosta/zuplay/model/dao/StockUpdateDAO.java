package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdateDAO {

	void priceMerge(PriceDTO priceDTO);
	
	List<String> getIsuSrtCd();
	
	void insertTrdPrc(PriceDTO priceDTO);

}
