package com.kosta.zuplay.model.service.stock;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.zuplay.model.dao.stock.DealHistoryDAO;
import com.kosta.zuplay.model.dto.stock.EarningRateHistoryDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

public class StockHistoryServiceImpl implements StockHistoryService {

	@Autowired
	private PlayerInfoService playerInfoService;
	
	@Autowired
	SqlSession sqlSession;
		
	@Override
	public List<EarningRateHistoryDTO> getEarningRateList(String playerNickname) throws Exception{
		DealHistoryDAO dealHistoryDAO = sqlSession.getMapper(DealHistoryDAO.class);
		return dealHistoryDAO.getEarningRateHistory(playerNickname);
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
