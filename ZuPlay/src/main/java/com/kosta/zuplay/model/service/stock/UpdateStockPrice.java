package com.kosta.zuplay.model.service.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dao.StockUpdateDAO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

@Service
public class UpdateStockPrice {

	@Autowired
	private SqlSession sqlSession;
	
	private StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
	
	public void updateStockPrice() {
		List<String> list = getIsuSrtCd();
		for(String isuSrtCd : list) {
			PriceDTO priceDTO = getPrice(isuSrtCd);
			updatePrice(priceDTO);
			insertTrdPrc(priceDTO);
		}
		
	}
	/**
	 * DB의 isuSerCd 가져오기
	 * */
	@Transactional
	public List<String> getIsuSrtCd() {
		
		return stockUpdateDAO.getIsuSrtCd();
	}
	/**
	 * DB의 price 테이블 업데이트
	 * */
	@Transactional
	public void updatePrice(PriceDTO price) {
		stockUpdateDAO.priceMerge(price);
	}

	/**
	 * isuSrtCd를 통해 주식오픈API에서 새로운 값 가져오기
	 * */
	public PriceDTO getPrice(String isuSrtCd) {
		URL url = null;
		PriceDTO price = null;
		try {
			url = new URL("https://testbed.koscom.co.kr:443/gateway/v1/market/stocks/price?isuSrtCd=" + isuSrtCd
					+ "&apikey=e7dd8ccc-0e25-4a91-adba-24049212c2b9");
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			Gson gson = new Gson();
			price = gson.fromJson(br, PriceDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	
	/**
	 * 체결가격 및 체결시간 REALTIME_PRICE 테이블에 삽입
	 * */
	@Transactional
	public void insertTrdPrc(PriceDTO priceDTO) {
		stockUpdateDAO.insertTrdPrc(priceDTO);
	}
}
