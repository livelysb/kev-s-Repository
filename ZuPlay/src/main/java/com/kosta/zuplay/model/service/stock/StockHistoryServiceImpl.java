package com.kosta.zuplay.model.service.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.stock.DealHistoryDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.EarningRateHistoryDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;
import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;
import com.kosta.zuplay.model.service.player.EarningRateService;

@Service
public class StockHistoryServiceImpl implements StockHistoryService {

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
		List<MasterDTO> best = new ArrayList<MasterDTO>();
		for (MasterDTO master : masterList) {
			if (master.getEarningRate() >= 0)
				best.add(master);
			else
				break;
		}
		return best;
	}

	@Override
	public List<MasterDTO> getWorst3(String playerNickname) throws Exception {
		List<MasterDTO> masterList = getEarningList(playerNickname);
		Collections.sort(masterList, new EarningCompareDesc());
		List<MasterDTO> worst = new ArrayList<MasterDTO>();
		for (MasterDTO master : masterList) {
			if (master.getEarningRate() <= 0)
				worst.add(master);
			else
				break;
		}
		return worst;
	}

	@Override
	public List<StockDealHistoryDTO> getStockDealHistory(String playerNickname, String orderBy, boolean asc, int page)
			throws Exception {
		DealHistoryDAO dealHistoryDAO = sqlSession.getMapper(DealHistoryDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("playerNickname : " + playerNickname);
		System.out.println("orderBy : " + orderBy);
		System.out.println("asc : " + asc);
		System.out.println("page : " + page);
		map.put("playerNickname", playerNickname);
		map.put("orderBy", orderBy);
		map.put("asc", String.valueOf(asc));
		map.put("startNum", 1 + ((page - 1) * 10) + "");
		map.put("lastNum", page * 10 + "");
		List<StockDealHistoryDTO> list = dealHistoryDAO.getStockHistoryOp(map);
		double feePercent = 0;
		for(StockDealHistoryDTO history : list) {
			if(history.getSdhBuySell().equals("b"))
				feePercent = 0.015;
			else
				feePercent = -0.315;
			history.getMasterDTO().setPriceDTO(new PriceDTO());
			history.getMasterDTO().getPriceDTO().setTrdPrc((int)(history.getSdhDealPrice() / (history.getSdhQuantity() * (1 + feePercent))));
		}
		return list;
	}

	public List<MasterDTO> getEarningList(String playerNickname) throws Exception {
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
		public int compare(MasterDTO m2, MasterDTO m1) {
			return m1.getEarningRate() > m2.getEarningRate() ? 1 : m1.getEarningRate() < m2.getEarningRate() ? -1 : 0;
		}
	}

	class EarningCompareDesc implements Comparator<MasterDTO> {
		@Override
		public int compare(MasterDTO m1, MasterDTO m2) {
			return m1.getEarningRate() > m2.getEarningRate() ? 1 : m1.getEarningRate() < m2.getEarningRate() ? -1 : 0;
		}
	}

}
