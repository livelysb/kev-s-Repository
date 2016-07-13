package com.kosta.zuplay.model.service.player;

public interface EarningRateService {

	/**
	 * 모든 플레이어들의 수익률 갱신 및 전일자산 업데이트
	 * */
	public int updateEarningRate() throws Exception;
	
	/**
	 * 금액을 통한 해당시즌 수익률 구하기
	 * */
	public double calEarningRate(String playerNickname) throws Exception;
	
	/**
	 * 플레이어의 일일 수익률 계산하기
	 * */
	public double calDailyEarningRate(String playerNickname) throws Exception;

	
	/**
	 * 플레이어의 종목별 수익률 계산하기
	 * */
	public double calItemEarningRate(String playerNickname, String isuCd) throws Exception;
	
	/**
	 * 플레이어의 종목별 총 수익률 계산하기
	 * */
	public int calItemEarningMoney(String playerNickname, String isuCd) throws Exception;
	
	/**
	 * 전일 자산 업데이트
	 * */
	boolean updatePreMoney(String playerNickname) throws Exception;
	
	/**
	 * 플레이어의 분야별 수익률 계산하기
	 * */
	public double calKindEarningRate(String playerNickname, int kind) throws Exception;
}
