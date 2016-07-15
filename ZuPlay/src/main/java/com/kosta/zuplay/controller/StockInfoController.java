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
import com.kosta.zuplay.model.service.stock.PlayerStockService;
import com.kosta.zuplay.model.service.stock.StockInfoService;

@Controller
public class StockInfoController {

	@Autowired
	private StockInfoService stockInfoService;
	
	@Autowired
	private PlayerStockService playerStockService;

	@ResponseBody
	@RequestMapping(value = "realTimeStock", produces = "application/json;charset=UTF-8")
	public String getStockList(HttpSession session, String page, String keyword) throws Exception{
		List<MasterDTO> masterList = null;
		try {
			masterList = stockInfoService.getStockList(Integer.parseInt(page), keyword);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(masterList);
		int amount = 877;
		if(!keyword.equals("undefined")) {
			try {
				amount = stockInfoService.getListSize(keyword);
			} catch (Exception e) {
				session.setAttribute("errorMsg", e.toString());
				e.printStackTrace();
				throw new Exception();
			}
		}
		String json2 = json.replace("[", "[{\"amount\":" + amount + "},");
		if(amount == 0) {
			return json;
		}
		return json2;
	}
	
	@RequestMapping(value = "companyInfo", produces = "application/json;charset=UTF-8")
	public ModelAndView getStock(HttpSession session, String isuCd) throws Exception{
		MasterDTO masterDTO = null;
		String playerNickname = (String)session.getAttribute("playerNickname");
		int plQuantity = 0;
		try {
			masterDTO = stockInfoService.getStockDetail(playerNickname, isuCd);
			plQuantity = playerStockService.getPlayerStock(playerNickname, isuCd).getPlQuantity();
		} catch(NullPointerException ne) {
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		ModelAndView mv = new ModelAndView("companyInfo");
		mv.addObject("masterDTO", masterDTO);
		mv.addObject("plQuantity", plQuantity);
		mv.addObject("rtpList", new Gson().toJson(masterDTO.getRtpList()));
		return mv;
	}
}
