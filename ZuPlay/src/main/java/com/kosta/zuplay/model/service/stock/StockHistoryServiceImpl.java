package com.kosta.zuplay.model.service.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.zuplay.model.dto.stock.EarningRateHistory;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

public class StockHistoryServiceImpl implements StockHistoryService {

	@Autowired
	private PlayerInfoService playerInfoService;
	
		
	@Override
	public List<EarningRateHistory> getEarningRateList(String playerNickname) throws Exception{
		
		return null;
	}

	@Override
	public List<MasterDTO> getBest3(String playerNickname) throws Exception {
		return null;
	}

	@Override
	public List<MasterDTO> getWorst3(String playerNickname) throws Exception {
		return null;
	}

	@Override
	public List<MasterDTO> getStockDealHistory(String playerNickname, String orderBy, boolean asc) throws Exception {
		return null;
	}

}
