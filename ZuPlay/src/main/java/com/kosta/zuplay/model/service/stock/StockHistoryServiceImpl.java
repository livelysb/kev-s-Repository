package com.kosta.zuplay.model.service.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.zuplay.model.dao.stock.DealHistoryDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.EarningRateHistoryDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;
import com.kosta.zuplay.model.service.player.EarningRateService;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

public class StockHistoryServiceImpl implements StockHistoryService {

	@Autowired
	private PlayerInfoService playerInfoService;

	@Autowired
	private PlayerStockService playerStockService;

	@Autowired
	private StockInfoService stockInfoService;

	@Autowired
	private EarningRateService earningRateService;

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<EarningRateHistoryDTO> getEarningRateList(String playerNickname) throws Exception {
		DealHistoryDAO dealHistoryDAO = sqlSession.getMapper(DealHistoryDAO.class);
		return dealHistoryDAO.getEarningRateHistory(playerNickname);
	}

	@Override
	public List<MasterDTO> getBest3(String playerNickname) throws Exception {
		List<MasterDTO> masterList = getEarningList(playerNickname);
		Collections.sort(masterList, new EarningCompareAsc());
		
		for(MasterDTO master : masterList){
			System.out.println(master.getIsuKorAbbrv());
			System.out.println(master.getEarningRate());
			System.out.println(master.getEarningMoney());
			System.out.println();
		}
		List<MasterDTO> best = new ArrayList<MasterDTO>();
		
		for(int i=0; i<3; i++) {
			if(masterList.get(i).getEarningRate()>=0)
				best.add(masterList.get(i));
		}
		return best;
	}

	@Override
	public List<MasterDTO> getWorst3(String playerNickname) throws Exception {
		List<MasterDTO> masterList = getEarningList(playerNickname);
		Collections.sort(masterList, new EarningCompareDesc());
		
		for(MasterDTO master : masterList){
			System.out.println(master.getIsuKorAbbrv());
			System.out.println(master.getEarningRate());
			System.out.println(master.getEarningMoney());
			System.out.println();
		}
		List<MasterDTO> worst = new ArrayList<MasterDTO>();
		
		for(int i=0; i<3; i++) {
			if(masterList.get(i).getEarningRate()>=0)
				worst.add(masterList.get(i));
		}
		return worst;
	}

	@Override
	public List<StockDealHistoryDTO> getStockDealHistory(String playerNickname, String orderBy, boolean asc, int page) throws Exception {
		DealHistoryDAO dealHistoryDAO = sqlSession.getMapper(DealHistoryDAO.class);
		Map<String, String>map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("orderBy", orderBy);
		map.put("asc", String.valueOf(asc));
		map.put("startNum", 1 + ((page - 1) * 10) + "");
		map.put("lastNum", page * 10 + "");
		return dealHistoryDAO.getStockHistoryOp(map);
	}
	
	public List<MasterDTO> getEarningList(String playerNickname) throws Exception{
		List<MasterDTO> masterList = new ArrayList<MasterDTO>();
		List<PlayerListsDTO> playerStockList = playerStockService.getPlayerStocks(playerNickname);
		for (PlayerListsDTO playerLists : playerStockList) {
			MasterDTO masterDTO = stockInfoService.getStockDetail(playerNickname, playerLists.getIsuCd());
			MasterDTO masterDTO2 = new MasterDTO();
			masterDTO2.setIsuKorAbbrv(masterDTO.getIsuKorAbbrv());
			masterDTO2.setKind(masterDTO.getKind());
			masterDTO2.setEarningRate(earningRateService.calItemEarningRate(playerNickname, playerLists.getIsuCd()));
			masterDTO2.setEarningMoney(earningRateService.calItemEarningMoney(playerNickname, playerLists.getIsuCd()));
			masterList.add(masterDTO2);
		}
		
		
		return masterList;
	}
	
	class EarningCompareAsc implements Comparator<MasterDTO> {
		@Override
		public int compare(MasterDTO m1, MasterDTO m2) {
			return m1.getEarningRate() > m2.getEarningRate() ? 1 : m1.getEarningRate() < m2.getEarningRate() ? -1 : 0;
		}
	}
	class EarningCompareDesc implements Comparator<MasterDTO> {
		@Override
		public int compare(MasterDTO m2, MasterDTO m1) {
			return m1.getEarningRate() > m2.getEarningRate() ? 1 : m1.getEarningRate() < m2.getEarningRate() ? -1 : 0;
		}
	}

}
