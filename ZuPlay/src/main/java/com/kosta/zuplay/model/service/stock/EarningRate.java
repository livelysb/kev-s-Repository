package com.kosta.zuplay.model.service.stock;

public interface EarningRate {

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
