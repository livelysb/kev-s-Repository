package com.kosta.zuplay.model.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dao.StockUpdateDAO;
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

@Service
public class StockInfoImpl implements StockInfo {

	@Autowired
	private SqlSession sqlSession;
	
	
	/**
	 * DB의 모든 prices 가져오기
	 */
	@Override
	public List<PriceDTO> getPrices() {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		return stockUpdateDAO.getPrices();
	}

	/**
	 * DB의 한 기업의 price 가져오기
	 * */
	@Override
	public PriceDTO getPrice(String isuCd) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		return stockUpdateDAO.getPrice(isuCd);
	}
	
	/**
	 * DB의 모든 isuSerCd 가져오기
	 */
	@Override
	public List<ListsDTO> getLists() {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		return stockUpdateDAO.getLists();
	}
	
	

	
	/**
	 * DB의 모든 주식리스트 보여주기
	 * */
	@Override
	public List<MasterDTO> getStockList(int page) {
		int startPage = (page-1)*10+1;
		int endPage = startPage + 9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		List<MasterDTO> masterList = stockUpdateDAO.getStockList(map);		
		for(MasterDTO masterDTO : masterList) {
			double fr = (masterDTO.getPriceDTO().getTrdPrc()*100)/(masterDTO.getPriceDTO().getTrdPrc()-masterDTO.getPriceDTO().getCmpprevddPrc())-100;
			int fr2 = (int)(fr*100);
			fr = (double)(fr2/100.0);
			masterDTO.getPriceDTO().setFluctuationRate(fr);
		}		
		return masterList;
	}

	
	/**
	 * 기업의 상세정보 보여주기
	 * */
	@Override
	public MasterDTO getStockDetail(String isuCd) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
