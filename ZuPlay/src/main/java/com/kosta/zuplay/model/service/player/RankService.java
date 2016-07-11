package com.kosta.zuplay.model.service.player;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

/**
 * 랭크 구하기
 * 일일 - 매일 아홉시에 수익률 테이블에서 가져옴
 * 시즌 - 시즌시작부터 지금까지의 수익률 테이블에서 가져옴
 * */
public interface RankService {

	/**
	 * 일일/시즌 랭크 매기기 - 매일 세시반에 모든 Player의 를 정렬하여 랭크 결정
	 * */
	void calRank(String kind) throws Exception;
	
	/**
	 * 랭크 조회하기
	 * */
	List<PlayerDTO> getRank(String kind) throws Exception;
	

	
	
}
