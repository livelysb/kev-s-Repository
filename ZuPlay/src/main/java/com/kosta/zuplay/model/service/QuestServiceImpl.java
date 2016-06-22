package com.kosta.zuplay.model.service;

import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.QuestDAO;
import com.kosta.zuplay.model.dto.QuestDTO;
import com.kosta.zuplay.model.dto.item.ItemDTO;

@Service
public class QuestServiceImpl implements QuestService {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private Random random;
	
	@Override
	public List<QuestDTO> questSelectAll(String playerNickname) {
		QuestDAO questDAO=sqlSession.getMapper(QuestDAO.class);
		return questDAO.questSelectAll(playerNickname);
	}

	@Override
	public boolean questSelect(String playerNickname, String questCode) {
		QuestDAO questDAO=sqlSession.getMapper(QuestDAO.class);
		QuestDTO quest=questDAO.questSelect(questCode);
		int result= questDAO.questSelectInsert(playerNickname, quest.getQuestCode());
		if(result==0){
			return false;
		}
		return true;
	}

	@Override
	public boolean questSelectRan(String playerNickname) {
		QuestDAO questDAO=sqlSession.getMapper(QuestDAO.class);
		int count=questDAO.questCount("common");
		int questCode=random.nextInt(count+1);
		return questSelect(playerNickname, Integer.toString(questCode));
	}

	@Override
	public ItemDTO questFin(String playerNickname, String questCode) {
		return null;
	}

}
