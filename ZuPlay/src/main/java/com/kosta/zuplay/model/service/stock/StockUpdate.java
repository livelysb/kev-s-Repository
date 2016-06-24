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
import com.kosta.zuplay.model.dto.stock.ListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

@Service
public class StockUpdate {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * DB의 isuSerCd 가져오기
	 */
	public List<ListsDTO> getLists() {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		return stockUpdateDAO.getLists();
	}

	/**
	 * DB의 price 가져오기
	 */
	public List<PriceDTO> getPrice() {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		return stockUpdateDAO.getPrice();
	}

	/**
	 * 실시간으로 주식정보를 업데이트 시킨다.
	 * */
	public void updateStockPrice() {
		List<ListsDTO> list = getLists();
		for (ListsDTO listsDTO : list) {
			PriceDTO priceDTO = getPriceFromAPI(listsDTO.getIsuSrtCd());
			updatePrice(priceDTO);
		}
		System.out.println("stock price is updated");

	}

	/**
	 * DB의 price 테이블 업데이트
	 */
	@Transactional
	public void updatePrice(PriceDTO price) {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		stockUpdateDAO.mergePrice(price);
	}

	/**
	 * isuSrtCd를 통해 주식오픈API에서 새로운 값 가져오기
	 */
	public PriceDTO getPriceFromAPI(String isuSrtCd) {
		URL url = null;
		PriceDTO price = null;
		try {
			url = new URL("https://testbed.koscom.co.kr:443/gateway/v1/market/stocks/price?isuSrtCd=" + isuSrtCd
					+ "&apikey=fa8835c0-1c9c-4268-a5f0-e11448cfb3b2");
			URLConnection conn = url.openConnection();
			try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))){
			Gson gson = new Gson();
			price = gson.fromJson(br, PriceDTO.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		
		}
		return price;
	}

	
	/**
	 * DB로부터 PRICE 받아와 REALTIME_PRICE에 삽입한다.
	 * */
	public void insertRealtimePrice() {
		List<PriceDTO> priceList = getPrice();
		for (PriceDTO priceDTO : priceList) {
			insertTrdPrc(priceDTO);
		}
		System.out.println("RealtimePrice is inserted");
	}

	/**
	 * 체결가격 및 체결시간 REALTIME_PRICE 테이블에 삽입
	 */
	@Transactional
	public void insertTrdPrc(PriceDTO priceDTO) {

		if (priceDTO.getTrdTm() != null) {
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			stockUpdateDAO.insertRealtimePrice(priceDTO);
		}

	}

	//////////////////////////////////////////////////////////////////// 9:30
	@Transactional
	public void updateMaster() {
		List<ListsDTO> lists = getLists();
		URL url = null;
		for (ListsDTO listsDTO : lists) {
			try {
				url = new URL("https://testbed.koscom.co.kr/gateway/v1/market/stocks/master?isuSrtCd="
						+ listsDTO.getIsuSrtCd() + "&apikey=fa8835c0-1c9c-4268-a5f0-e11448cfb3b2");
				URLConnection conn = url.openConnection();
				try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))){
				Gson gson = new Gson();
				MasterDTO masterDTO = gson.fromJson(br, MasterDTO.class);			
				StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
				stockUpdateDAO.mergeMaster(masterDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Master info is updated");
	}
	
	@Transactional
	public void resetRealtimePrice() {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		stockUpdateDAO.resetRealtimePrice();
		System.out.println("RealtimePrice is reseted");
	}

	////////////////////////////////////////////////////////////////////// 3:30
	@Transactional
	public void insertDailyPrice() {
		List<PriceDTO> priceList = getPrice();
		for (PriceDTO priceDTO : priceList) {
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			stockUpdateDAO.insertDailyPrice(priceDTO);
		}
		System.out.println("DailyPrice is inserted");
	}
}