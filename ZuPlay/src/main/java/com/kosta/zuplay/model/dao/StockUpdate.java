package com.kosta.zuplay.model.dao;

import com.kosta.zuplay.model.dto.stock.PriceDTO;

public interface StockUpdate {

	void priceMerge(PriceDTO price);

}
