package com.kosta.zuplay.model.service.stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dao.stock.StockUpdateDAO;
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

@Service
public class StockUpdateServiceImpl implements StockUpdateService{

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private StockInfoService stockInfoService;

	
	@Transactional
	public void stockPriceUpdate() {
		List<ListsDTO> list = stockInfoService.getLists();
		for (ListsDTO listsDTO : list) {
			PriceDTO priceDTO = getPriceFromAPI(listsDTO.getIsuSrtCd());
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			stockUpdateDAO.priceUpdate(priceDTO);
		}
		System.out.println(new Date().toString() +" : Price is updated");

	}

	public PriceDTO getPriceFromAPI(String isuSrtCd) {
		URL url = null;
		PriceDTO price = null;
		BufferedReader br = null;
		try {
			url = new URL("https://testbed.koscom.co.kr:443/gateway/v1/market/stocks/price?isuSrtCd=" + isuSrtCd
					+ "&apikey=fa8835c0-1c9c-4268-a5f0-e11448cfb3b2");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			Gson gson = new Gson();
			price = gson.fromJson(br, PriceDTO.class);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return price;
	}

	@Transactional
	public void realtimePriceInsert() {
		List<PriceDTO> priceList = stockInfoService.getPrices();
		for (PriceDTO priceDTO : priceList) {
			if (priceDTO.getTrdTm() != null) {
				StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
				stockUpdateDAO.realtimePriceInsert(priceDTO);
			}
		}
		System.out.println(new Date().toString() +" : RealtimePrice is inserted");
	}
	
	@Transactional
	public void realtimePriceReset() {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		stockUpdateDAO.realtimePriceReset();
		System.out.println(new Date().toString() + " : RealtimePrice is reseted");
	}

	@Transactional
	public void masterUpdate() {
		List<ListsDTO> lists = stockInfoService.getLists();
		for(ListsDTO listDTO : lists) {
			MasterDTO masterDTO = getMasterFromAPI(listDTO.getIsuSrtCd());
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			stockUpdateDAO.masterUpdate(masterDTO);
		}
		System.out.println(new Date().toString() + " : Master is updated");
	}
	
	public MasterDTO getMasterFromAPI(String isuSrtCd) {
		URL url = null;
		BufferedReader br = null;
		MasterDTO masterDTO = null;
			try {
				url = new URL("https://testbed.koscom.co.kr/gateway/v1/market/stocks/master?isuSrtCd="
						+ isuSrtCd + "&apikey=fa8835c0-1c9c-4268-a5f0-e11448cfb3b2");
				URLConnection conn = url.openConnection();
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				Gson gson = new Gson();
				masterDTO = gson.fromJson(br, MasterDTO.class);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return masterDTO;
	}

	@Transactional
	public void dailyPriceInsert() {
		List<PriceDTO> priceList = stockInfoService.getPrices();
		for (PriceDTO priceDTO : priceList) {
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			stockUpdateDAO.dailyPriceInsert(priceDTO);
		}
		System.out.println(new Date().toString() + " : DailyPrice is inserted");
	}
}
