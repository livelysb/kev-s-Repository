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
public class StockInfoController {

	@Autowired
	private StockInfoService stockInfo;

	@ResponseBody
	@RequestMapping(value = "realTimeStock", produces = "application/json;charset=UTF-8")
	public String getStockList(String page, String keyword) {
		System.out.println("page : " + page + ", keyword : " + keyword);
		List<MasterDTO> masterList = stockInfo.getStockList(Integer.parseInt(page), keyword);
		Gson gson = new Gson();
		String json = gson.toJson(masterList);
		System.out.println(json);
		int amount = 877;
		if(!keyword.equals("undefined")) {
			amount = stockInfo.getListSize(keyword);
		}
		String json2 = json.replace("[", "[{\"amount\":" + amount + "},");
		System.out.println(json2);

		return json2;
	}
}
