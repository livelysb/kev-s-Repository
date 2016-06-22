package com.kosta.zuplay.model.service;

import java.util.List;

import com.kosta.zuplay.model.dto.QuestDTO;
import com.kosta.zuplay.model.dto.item.ItemDTO;

public interface QuestService {

	/**
	 * 퀘스트 조회
	 */
	List<QuestDTO> questSelectAll(String playerNickname);
	
	/**
	 * 지정퀘스트 뽑기
	 */
	boolean questSelect(String playerNickname, String questCode);
	
	/**
	 * 랜덤퀘스트 뽑기
	 */
	boolean questSelectRan(String playerNickname);
	
	/**
	 * 퀘스트 완료
	 */
	ItemDTO questFin(String playerNickname,String questCode);
}
