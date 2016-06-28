package com.kosta.zuplay.model.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.stock.StockInfoDAO;
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

@Service
public class StockInfoServiceImpl implements StockInfoService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<PriceDTO> getPrices() {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getPrices();
	}

	@Override
	public PriceDTO getPrice(String isuCd) {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getPrice(isuCd);
	}
	
	@Override
	public List<ListsDTO> getLists() {
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		return stockInfoDAO.getLists();
	}

	@Override
	public List<MasterDTO> getStockList(int page) {
		int startPage = (page-1)*10+1;
		int endPage = startPage + 9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		StockInfoDAO stockInfoDAO = sqlSession.getMapper(StockInfoDAO.class);
		List<MasterDTO> masterList = stockInfoDAO.getStockList(map);		
		for(MasterDTO masterDTO : masterList) {
			double fr = (masterDTO.getPriceDTO().getTrdPrc()*100)/(masterDTO.getPriceDTO().getTrdPrc()-masterDTO.getPriceDTO().getCmpprevddPrc())-100;
			int fr2 = (int)(fr*100);
			fr = (double)(fr2/100.0);
			masterDTO.getPriceDTO().setFluctuationRate(fr);
		}		
		return masterList;
	}


	@Override
	public MasterDTO getStockDetail(String isuCd) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
