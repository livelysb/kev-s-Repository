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
public class StockUpdateServiceImpl implements StockUpdateService {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private StockInfoService stockInfoService;

	@Transactional
	public void stockPriceUpdate() throws Exception {
		List<ListsDTO> list = stockInfoService.getLists();
		for (ListsDTO listsDTO : list) {
			PriceDTO priceDTO = getPriceFromAPI(listsDTO.getIsuSrtCd());
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			if (priceDTO != null)
				stockUpdateDAO.priceUpdate(priceDTO);
		}
		System.out.println(new Date().toString() + " : Price is updated");

	}

	public PriceDTO getPriceFromAPI(String isuSrtCd) throws Exception {
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

		} catch (IOException ioe) {
			System.out.println("price - 429(불량) 응답으로 인한 한 종목 업데이트 갱신되지 않음");
			// 429에러이니 무시하기^^;
			// System.out.println(ioe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return price;
	}

	@Transactional
	public void realtimePriceInsert() throws Exception {
		List<PriceDTO> priceList = stockInfoService.getPrices();
		for (PriceDTO priceDTO : priceList) {
			if (priceDTO.getTrdTm() != null) {
				StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
				stockUpdateDAO.realtimePriceInsert(priceDTO);
			}
		}
		System.out.println(new Date().toString() + " : RealtimePrice is inserted");
	}

	@Transactional
	public void realtimePriceReset() throws Exception {
		StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
		stockUpdateDAO.realtimePriceReset();
		System.out.println(new Date().toString() + " : RealtimePrice is reseted");
	}

	@Transactional
	public void masterUpdate() throws Exception {
		List<ListsDTO> lists = stockInfoService.getLists();
		for (ListsDTO listDTO : lists) {
			MasterDTO masterDTO = getMasterFromAPI(listDTO.getIsuSrtCd());
			if (masterDTO != null) {
				StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
				stockUpdateDAO.masterUpdate(masterDTO);
				System.out.println(masterDTO.getIsuKorAbbrv());
			}
		}
		System.out.println(new Date().toString() + " : Master is updated");
	}

	public MasterDTO getMasterFromAPI(String isuSrtCd) throws Exception {
		URL url = null;
		BufferedReader br = null;
		MasterDTO masterDTO = null;
		try {
			url = new URL("https://testbed.koscom.co.kr/gateway/v1/market/stocks/master?isuSrtCd=" + isuSrtCd
					+ "&apikey=fa8835c0-1c9c-4268-a5f0-e11448cfb3b2");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			Gson gson = new Gson();
			masterDTO = gson.fromJson(br, MasterDTO.class);

		} catch (IOException ioe) {
			System.out.println("master - 429(불량) 응답으로 인한 한 종목 업데이트 갱신되지 않음");
			// 429에러이니 무시하기^^;
			// System.out.println(ioe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// return masterMatchKind(masterDTO); - kind 분류시킬때 사용
		return masterDTO;
	}

	@Transactional
	public void dailyPriceInsert() throws Exception {
		List<PriceDTO> priceList = stockInfoService.getPrices();
		for (PriceDTO priceDTO : priceList) {
			StockUpdateDAO stockUpdateDAO = sqlSession.getMapper(StockUpdateDAO.class);
			stockUpdateDAO.dailyPriceInsert(priceDTO);
		}
		System.out.println(new Date().toString() + " : DailyPrice is inserted");
	}

	@Override
	public MasterDTO masterMatchKind(MasterDTO masterDTO) throws Exception {
		if (masterDTO.getKrxAutosSectidxYn().equals("Y")) {
			masterDTO.setKind("자동차");
		} else if (masterDTO.getKrxTransSectidxYn().equals("Y")) {
			masterDTO.setKind("운송");
		} else if (masterDTO.getKrxSteelSectidxYn().equals("Y")) {
			masterDTO.setKind("철강");
		} else if (masterDTO.getKrxBioSectidxYn().equals("Y")) {
			masterDTO.setKind("바이오");
		} else if (masterDTO.getKrxConsgoodSectidxYn().equals("Y")) {
			masterDTO.setKind("소비재");
		} else if (masterDTO.getKrxConstrSectidxYn().equals("Y")) {
			masterDTO.setKind("건설");
		} else if (masterDTO.getKrxEnergyChemSectidxYn().equals("Y")) {
			masterDTO.setKind("에너지화학");
		} else if (masterDTO.getKrxFncSectidxYn().equals("Y")) {
			masterDTO.setKind("금융");
		} else if (masterDTO.getKrxFncSvcSectidxYn().equals("Y")) {
			masterDTO.setKind("금융서비스");
		} else if (masterDTO.getKrxInfoCommSectidxYn().equals("Y")) {
			masterDTO.setKind("정보통신");
		} else if (masterDTO.getKrxInsuSectidxYn().equals("Y")) {
			masterDTO.setKind("보험");
		} else if (masterDTO.getKrxLeisureSectidxYn().equals("Y")) {
			masterDTO.setKind("레저엔터테인먼트");
		} else if (masterDTO.getKrxMediaCommSectidxYn().equals("Y")) {
			masterDTO.setKind("미디어통신");
		} else if (masterDTO.getKrxRetailSectidxYn().equals("Y")) {
			masterDTO.setKind("소비자유통");
		} else if (masterDTO.getKrxSecuSectidxYn().equals("Y")) {
			masterDTO.setKind("증권");
		} else if (masterDTO.getKrxSemiconSectidxYn().equals("Y")) {
			masterDTO.setKind("반도체");
		} else if (masterDTO.getKrxShipSectidxYn().equals("Y")) {
			masterDTO.setKind("선박");
		} else
			masterDTO.setKind("기타");
		return masterDTO;
	}
}
