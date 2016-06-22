package com.kosta.zuplay.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.QuestDTO;
import com.kosta.zuplay.model.dto.item.ItemDTO;

@Controller
public class QuestController {
	/**
	 * 퀘스트 조회
	 */
	@RequestMapping("questSelectAll")
	public List<QuestDTO> qusetSelectAll(String playerNickname){
		return null;
	}
	/**
	 * 지정 퀘스트 뽑기
	 * */
	@RequestMapping("questSelect")
	@ResponseBody
	public boolean questSelect(String playerNickname, String questCode,String questClass){
		return true;
	}
	
	/**
	 * 랜덤 퀘스트 뽑기
	 */
	@RequestMapping("questSelectRan")
	@ResponseBody
	public boolean questSelectRan(String playerNickname){
		return true;
	}
	
	/**
	 * 퀘스트 완료하기
	 */
	@RequestMapping("questFin")
	@ResponseBody
	public ItemDTO questFin(String playerNickname,String questCode){
		return null;
	}
}
