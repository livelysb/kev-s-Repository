package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.QuestDTO;

public interface QuestDAO {
	List<QuestDTO> questSelectAll(String playerNickname);
	int questSelectInsert(String playerNickname, String questCode);
	String questFin(String playerNickname,String questCode);//리턴:아이템코드
	QuestDTO questSelect(String questCode);
	int questCount(String questClass);
}
