package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.service.stock.StockTradeService;

@Controller
public class StockTradeController {
	
	@Autowired
	private StockTradeService stockTradeService;
	
	@RequestMapping(value="buyStock", produces="application/json;charset=UTF-8")
	@ResponseBody
	public boolean butStock(HttpSession session, String isuCd, int plQuantity) throws Exception{
		String playerNickname = (String)session.getAttribute("playerNickname");
		try {
			return stockTradeService.buyStock(playerNickname, isuCd, plQuantity);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
	}
	
	@RequestMapping(value="sellStock", produces="application/json;charset=UTF-8")
	@ResponseBody
	public boolean sellStock(HttpSession session, String isuCd, int plQuantity) throws Exception {
		String playerNickname = (String)session.getAttribute("playerNickname");
		return false;
	}
	
}
