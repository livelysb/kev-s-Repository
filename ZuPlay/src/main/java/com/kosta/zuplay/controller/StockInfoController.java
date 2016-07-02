package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		List<MasterDTO> masterList = stockInfo.getStockList(Integer.parseInt(page), keyword);
		Gson gson = new Gson();
		String json = gson.toJson(masterList);
		int amount = 877;
		if(!keyword.equals("undefined")) {
			amount = stockInfo.getListSize(keyword);
		}
		String json2 = json.replace("[", "[{\"amount\":" + amount + "},");
		if(amount == 0) {
			return json;
		}
		return json2;
	}
	
	@RequestMapping(value = "companyInfo", produces = "application/json;charset=UTF-8")
	public ModelAndView getStock(HttpSession session, String isuCd) {
		MasterDTO masterDTO = stockInfo.getStockDetail((String)session.getAttribute("playerNickname"), isuCd);
		ModelAndView mv = new ModelAndView("companyInfo");
		mv.addObject("masterDTO", masterDTO);
		return mv;
	}
}
