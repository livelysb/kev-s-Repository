package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StockUpdateServiceImpl implements StockUpdateService {
	
	@Autowired
	private UpdateStockPrice updateStockPrice;
		
	/**
	 * 작동빈도 : 작업이 끝난 시점으로 1분 뒤 재시작한다.
	 * 수행사항 : price 값 받아와서 PRICE, REALTIME_PRICE 테이블을 update, insert를 진행한다.
	 * */
	@Scheduled(fixedDelay=60000) //작업이 끝난지 1분 후 재시작
	@Override
	public void actionPerMin() {
		updateStockPrice.updateStockPrice11();
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
}
