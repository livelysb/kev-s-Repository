package com.kosta.zuplay.model.service.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dao.StockUpdate;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

@Service
public class StockUpdateServiceImpl implements StockUpdateService {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 작동빈도 : 작업이 끝난 시점으로 1분 뒤 재시작한다.
	 * 수행사항 : price 값 받아와서 PRICE, REALTIME_PRICE 테이블을 update, insert를 진행한다.
	 * */
	@Scheduled(fixedDelay=60000) //작업이 끝난지 1분 후 재시작
	@Override
	public void actionPerMin() {
		
	}

	@Scheduled(cron="0 30 9 * * *") //매일 아홉시반에 작동
	@Override
	public void actionAtNine() {
		//
	}

	@Scheduled(cron="0 30 15 * * *") //매일 세시반에 작동
	@Override
	public void actionAtFour() {
		
	}

	
	@Transactional
	public void updatePrice(PriceDTO price) {
		StockUpdate stock = sqlSession.getMapper(StockUpdate.class);
		stock.priceMerge(price);
	}

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

}
