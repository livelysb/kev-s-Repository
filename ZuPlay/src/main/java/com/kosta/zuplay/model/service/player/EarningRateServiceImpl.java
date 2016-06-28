package com.kosta.zuplay.model.service.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.EarningRateDAO;
import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;
import com.kosta.zuplay.model.service.stock.DealHistoryService;
import com.kosta.zuplay.model.service.stock.PlayerStockService;
import com.kosta.zuplay.model.service.stock.StockInfoService;


@Service
public class EarningRateServiceImpl implements EarningRateService {

	@Autowired
	private PlayerInfoService playerInfoService;
	
	@Autowired
	private PlayerStockService playerStockService;
	
	@Autowired
	private StockInfoService stockInfoService;
	
	@Autowired
	private DealHistoryService dealHistoryService;
	
	@Autowired
	private SqlSession sqlSession;
	
	

	
	/**
	 * 모든 플레이어들의 수익률 갱신 및 전일자산 업데이트
	 * */
	@Override
	public int updateEarningRate() {
		int result = 0;
		List<String> playerList = playerInfoService.getAllPlayerNickName();
		for(String playerNickname : playerList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("playerNickname", playerNickname);
			map.put("erhPe", Double.toString(calDailyEarningRate(playerNickname)));
			EarningRateDAO earningRateDAO = sqlSession.getMapper(EarningRateDAO.class);
			if(earningRateDAO.earningRateInsert(map)>0)
				if(updatePreMoney(playerNickname))
					result ++;
	
		}
		
		return result;
	}
	
	/**
	 * 전일 자산 업데이트
	 * */
	public boolean updatePreMoney(String playerNickname) {
		Map<String, String> map = new HashMap<String, String>();
		int totalMoney = playerInfoService.getTotalMoney(playerNickname);
		map.put("playerNickname", playerNickname);
		map.put("preMoney", Integer.toString(totalMoney));
		PlayerInfoDAO playerInfoDAO = sqlSession.getMapper(PlayerInfoDAO.class);
		if(playerInfoDAO.setPreMoney(map)>0)
			return true;
		return false;
	}
	
	
	/**
	 * 금액을 통한 해당시즌 수익률 구하기
	 * 현재 금액, 보유주식의 현재 체결가를 이용한 금액
	 * */
	@Override
	public double calEarningRate(String playerNickname) {
		int startMoney = 100000000;
		int currentMoney = playerInfoService.getTotalMoney(playerNickname);
		int rate = (int)((currentMoney - startMoney) / (double)(startMoney) * 10000);
		return rate /100.0;
	}

	/**
	 * 플레이어의 일일 수익률 계산하기
	 * */
	@Override
	public double calDailyEarningRate(String playerNickname) {
		int preMoney = playerInfoService.getPlayer(playerNickname).getPlayerPreMoney();
		int currentMoney = playerInfoService.getTotalMoney(playerNickname);
		int rate = (int)(( currentMoney- preMoney)/(double)(preMoney) * 10000);
		return rate/100.0;
	}

	/**
	 * 플레이어의 종목별 수익률 계산하기
	 * */
	@Override
	public double calItemEarningRate(String playerNickname, String isuCd) {
		int buy = 0;
		int sell = 0;
		List<StockDealHistoryDTO> stockDealHistoryList = dealHistoryService.getStockHistory(playerNickname);
		for(StockDealHistoryDTO stockDealHistory : stockDealHistoryList) {
			if(stockDealHistory.getSdhBuySell().equals("b"))
				buy += stockDealHistory.getSdhDealPrice();
			else 
				sell += stockDealHistory.getSdhDealPrice();
		}
		sell += playerStockService.getPlayerStock(playerNickname, isuCd).getPlQuantity() * stockInfoService.getPrice(isuCd).getTrdPrc();
		int rate = (int)((sell - buy) / (double)(buy) * 10000);
		return rate/100.0;
	}

	/**
	 * 플레이어의 분야별 수익률 계산하기
	 * */
	@Override
	public double calKindEarningRate(String playerNickname, int kind) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
