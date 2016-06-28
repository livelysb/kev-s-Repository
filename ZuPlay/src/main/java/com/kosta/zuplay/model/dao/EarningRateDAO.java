package com.kosta.zuplay.model.dao;

import java.util.Map;

public interface EarningRateDAO {

	/**
	 * 전일 수익률 삽입
	 * */
	public int earningRateInsert(Map<String, String> map);
}
