package com.kosta.zuplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.service.stock.StockInfoService;

@Controller
public class StockStockInfoController {
	
	@Autowired
	private StockInfoService stockInfo;
	
	@ResponseBody
	@RequestMapping(value="realTimeStock" ,produces="application/json;charset=UTF-8" )
	public String getStockList(String page) {
		List<MasterDTO>masterList = stockInfo.getStockList(Integer.parseInt(page));
		Gson gson = new Gson();
		String json = gson.toJson(masterList);
		return json;
	}
}
