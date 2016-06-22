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
	@Scheduled(fixedDelay=5*60*1000) //작업이 끝난지 1분 후 재시작
	@Override
	public void actionPerMin() {
		updateStockPrice.updateStockPrice();
	}
	
	/**
	 * 수행사항 : 실시간 주식정보를 쌓아 일일 체결가 그래프를 그릴 수 있다.
	 * */
	@Scheduled(fixedDelay=10*60*1000)//10분
	@Override
	public void actionPer10Min() {
		updateStockPrice.insertRealtimePrice();
	}
	
	/**
	 * 수행사항
	 * 1. 마스터정보를 업데이트한다.
	 * 2. 장을 오픈한다. ( 거래 활성화 )
	 * */
	@Scheduled(cron="0 30 9 * * *") //매일 아홉시반에 작동
	@Override
	public void actionAtNine() {
		System.out.println("되나?");
		updateStockPrice.updateMaster();
	}

	/**
	 * 수행사항
	 * 1. 현재 체결가를 조회하여 DAILY_PRICE 테이블에 저장한다.
	 * */

	@Scheduled(cron="0 30 15 * * *") //매일 세시반에 작동
	@Override
	public void actionAtFour() {
		updateStockPrice.insertDailyPrice();
	}
	
}
