package com.kosta.zuplay.model.service.stock;

public interface EarningRate {

	/**
	 * 모든 플레이어들의 수익률 갱신 및 전일자산 업데이트
	 * */
	public int updateEarningRate();
	
	/**
	 * 금액을 통한 해당시즌 수익률 구하기
	 * */
	public double calEarningRate(String playerNickname);
	
	/**
	 * 플레이어의 일일 수익률 계산하기
	 * */
	public double calDailyEarningRate(String playerNickname);

	
	/**
	 * 플레이어의 종목별 수익률 계산하기
	 * */
	public double calItemEarningRate(String playerNickname, String isuCd);
	
	
	/**
	 * 플레이어의 분야별 수익률 계산하기
	 * */
	public double calKindEarningRate(String playerNickname, int kind);
}
