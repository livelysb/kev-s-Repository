package com.kosta.zuplay.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.QuestDTO;
import com.kosta.zuplay.model.dto.item.ItemDTO;
import com.kosta.zuplay.model.service.QuestService;
import com.kosta.zuplay.model.service.QuestServiceImpl;

@Controller
public class QuestController {
	
	@Autowired
	private QuestService questServiceImpl;
	
	/**
	 * 퀘스트 조회
	 */
	@RequestMapping("questSelectAll")
	@ResponseBody
	public List<QuestDTO> qusetSelectAll(String playerNickname){
		return questServiceImpl.questSelectAll(playerNickname);
	}
	/**
	 * 지정 퀘스트 뽑기
	 * */
	@RequestMapping("questSelect")
	@ResponseBody
	public boolean questSelect(String playerNickname, String questCode){
		return questServiceImpl.questSelect(playerNickname, questCode);
	}
	
	/**
	 * 랜덤 퀘스트 뽑기
	 */
	@RequestMapping("questSelectRan")
	@ResponseBody
	public boolean questSelectRan(String playerNickname){
		return questServiceImpl.questSelectRan(playerNickname);
	}
	
	/**
	 * 퀘스트 완료하기
	 */
	@RequestMapping("questFin")
	@ResponseBody
	public ItemDTO questFin(String playerNickname,String questCode){
		return questServiceImpl.questFin(playerNickname, questCode);
	}
}
