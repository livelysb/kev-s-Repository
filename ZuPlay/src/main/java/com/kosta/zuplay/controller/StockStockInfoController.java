package com.kosta.zuplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.service.stock.GetStockInfo;

@Controller
public class StockStockInfoController {
	
	@Autowired
	private GetStockInfo getStockInfo;
	
	@RequestMapping("realTimeStock")
	public String getStockList(int page) {
		List<MasterDTO>masterList = getStockInfo.getStockList(page);
		Gson gson = new Gson();
		String json = gson.toJson(masterList);
		return json;
	}
}
