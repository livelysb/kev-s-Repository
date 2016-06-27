package com.kosta.zuplay.model.service.stock;

/**
 * 주식 관련 정보를 API로부터 받아와 업데이트 시키는 클래스
 * */
public interface StockUpdateService {
	
	/**
	 * 작동빈도 : 작업이 끝난 시점으로 1분 뒤 재시작한다.
	 * 수행사항 : price 값 받아와서 PRICE, REALTIME_PRICE 테이블을 update, insert를 진행한다.
	 * */
	public void actionPer5Min();
	
	/**
	 * 수행사항 : 실시간 주식정보를 쌓아 일일 체결가 그래프를 그릴 수 있다.
	 * */
	public void actionPer10Min();
	
	/**
	 * 수행사항
	 * 1. 마스터정보를 업데이트한다.
	 * 2. 어제의 실시간 체결가를 초기화한다.
	 * 2. 장을 오픈한다. ( 거래 활성화 )
	 * */
	public void actionAtNine();
	
	/**
	 * 수행사항
	 * 1. 현재 체결가를 조회하여 DAILY_PRICE 테이블에 저장한다.
	 * */
	public void actionAtFour();
	
}
