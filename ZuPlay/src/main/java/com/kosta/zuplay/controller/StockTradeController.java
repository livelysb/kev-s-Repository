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
	public String butStock(HttpSession session, String isuCd, int plQuantity) throws Exception{		
		String playerNickname = (String)session.getAttribute("playerNickname");
		try {
			if(stockTradeService.buyStock(playerNickname, isuCd, plQuantity))
				return plQuantity + " 주가 성공적으로 구매되었습니다.";
			else
				return "정상적으로 구매 처리되지 않았습니다. 다시 시도해 주세요.";
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
	}
	
	@RequestMapping(value="sellStock", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String sellStock(HttpSession session, String isuCd, int plQuantity) throws Exception {
		String playerNickname = (String)session.getAttribute("playerNickname");
		try {
			if(stockTradeService.sellStock(playerNickname, isuCd, plQuantity))
				return plQuantity + " 주를 성공적으로 판매하였습니다.";
			else 
				return "정상적으로 판매 처리되지 않았습니다. 다시 시도해 주세요.";
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
	}
	
}
