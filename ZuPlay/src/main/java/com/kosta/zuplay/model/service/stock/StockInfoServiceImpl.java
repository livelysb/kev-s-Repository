package com.kosta.zuplay.model.service.stock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.stock.PlayerStockDAO;
import com.kosta.zuplay.model.dao.stock.StockInfoDAO;
import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.DailyPriceDTO;
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;
import com.kosta.zuplay.model.dto.stock.RealTimePriceDTO;

@Service
public class StockInfoServiceImpl implements StockInfoService {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private PlayerStockService playerStockService;

	@Override
	public List<PriceDTO> getPrices() throws Exception {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getPrices();
	}

	@Override
	public PriceDTO getPrice(String isuCd) throws Exception {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getPrice(isuCd);
	}

	@Override
	public List<ListsDTO> getLists() throws Exception {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getLists();
	}

	@Override
	public List<MasterDTO> getStockList(int page, String keyword) throws Exception {
		int startPage = (page - 1) * 10 + 1;
		int endPage = startPage + 9;
		Map<String, String> map = new HashMap<String, String>();
		map.put("startPage", Integer.toString(startPage));
		map.put("endPage", Integer.toString(endPage));
		map.put("isuKorAbbrv", keyword);
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		List<MasterDTO> masterList = stockInfoDAO.getStockList(map);
		for (MasterDTO masterDTO : masterList) {

			// 등락률
			double fr = (masterDTO.getPriceDTO().getTrdPrc() * 100)
					/ (masterDTO.getPriceDTO().getTrdPrc() - masterDTO.getPriceDTO().getCmpprevddPrc()) - 100;
			int fr2 = (int) (fr * 100);
			fr = (double) (fr2 / 100.0);
			masterDTO.getPriceDTO().setFluctuationRate(fr);

			// 좋아하는 기업인지
			PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
			List<String> stockLikeList = playerStockDAO.getLikeStock(masterDTO.getIsuCd());
			for (String isuCd2 : stockLikeList) {
				if (isuCd2.equals(masterDTO.getIsuCd()))
					masterDTO.setLike(true);
			}
		}
		return masterList;
	}

	@Override
	public MasterDTO getStockDetail(String playerNickname, String isuCd) throws Exception {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		MasterDTO masterDTO = stockInfoDAO.getStock(isuCd);
		masterDTO.setDpList(stockInfoDAO.getDPList(isuCd));
		masterDTO.setRtpList(stockInfoDAO.getRTPList(isuCd));
		for(RealTimePriceDTO rtp : masterDTO.getRtpList()) {
			rtp.setRpTrdTm2(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), Integer.parseInt(rtp.getRpTrdTm().split(":")[0]), Integer.parseInt(rtp.getRpTrdTm().split(":")[1]), Integer.parseInt(rtp.getRpTrdTm().split(":")[2])).getTime());
		}
		for(DailyPriceDTO dp : masterDTO.getDpList()) {
			String s[] = dp.getDpDate().split(" ");
			dp.setDpDate2(new Date(Integer.parseInt(s[0].split("-")[0])-1900, Integer.parseInt(s[0].split("-")[1])-1, Integer.parseInt(s[0].split("-")[2])+1, Integer.parseInt(s[1].split(":")[0]), Integer.parseInt(s[1].split(":")[1]), Integer.parseInt(s[1].split(":")[2])).getTime());
		}

		// 좋아하는 기업인지
		PlayerStockDAO playerStockDAO = sqlSession.getMapper(PlayerStockDAO.class);
		List<String> stockLikeList = playerStockDAO.getLikeStock(isuCd);
		for (String isuCd2 : stockLikeList) {
			if (isuCd2.equals(isuCd))
				masterDTO.setLike(true);
		}

		// 등락률
		double fr = (masterDTO.getPriceDTO().getTrdPrc() * 100)
				/ (masterDTO.getPriceDTO().getTrdPrc() - masterDTO.getPriceDTO().getCmpprevddPrc()) - 100;
		int fr2 = (int) (fr * 100);
		fr = (double) (fr2 / 100.0);
		masterDTO.getPriceDTO().setFluctuationRate(fr);

		// 플레이어가 가진 해당 주식 수량
		PlayerListsDTO playerListsDTO = playerStockService.getPlayerStock(playerNickname, isuCd);
		if (playerListsDTO == null)
			masterDTO.setPlQuantity(0);
		else
			masterDTO.setPlQuantity(playerStockService.getPlayerStock(playerNickname, isuCd).getPlQuantity());
		return masterDTO;

	}

	@Override
	public int getListSize(String isuKorAbbrv) throws Exception {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getListSize(isuKorAbbrv);
	}

}
