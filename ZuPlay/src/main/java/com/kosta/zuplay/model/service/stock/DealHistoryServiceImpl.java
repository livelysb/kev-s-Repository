package com.kosta.zuplay.model.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.stock.DealHistoryDAO;
import com.kosta.zuplay.model.dto.stock.StockDealHistoryDTO;

@Service
public class DealHistoryServiceImpl implements DealHistoryService {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean stockHistoryInsert(String playerNickname, String isuCd, int plQuantity, int price, String bs) {
		DealHistoryDAO dealHistoryDAO = sqlSession.getMapper(DealHistoryDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("isuCd", isuCd);
		map.put("plQuantity", Integer.toString(plQuantity));
		map.put("price", Integer.toString(price));
		map.put("bs", bs);
		if(dealHistoryDAO.stockHistoryInsert(map)>0)
			return true;
		return false;
	}
	
	@Override
	public List<StockDealHistoryDTO> getStockHistory(String playerNickname) {
		DealHistoryDAO dealHistoryDAO = sqlSession.getMapper(DealHistoryDAO.class);
		return dealHistoryDAO.getStockHistory(playerNickname);
	}

}
