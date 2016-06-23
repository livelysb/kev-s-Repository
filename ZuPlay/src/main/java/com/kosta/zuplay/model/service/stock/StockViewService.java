package com.kosta.zuplay.model.service.stock;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;
import com.kosta.zuplay.model.dto.stock.MasterDTO;

/**
 * 클래스 역할
 * 1. 플레이어가 가지고 있는 주식 정보 보여주기
 * 2. 주식메뉴에서 필요한 주식정보 보여주기
 * 2.1. 주식 리스트 보여주기
 * 2.2. 기업의 상세정보 보여주기
 * */
public interface StockViewService {
	
	/**
	 * 플레이어가 가지는 주식정보 보여주기
	 * */
	public PlayerListsDTO getStockOfPlayer(String playerNickname);
	
	/**
	 * 2.1. 주식리스트 보여주기
	 * */
	public List<MasterDTO> getStockList(int Page);
	
	/**
	 * 2.2. 기업의 상세정보 보여주기
	 * */
	public MasterDTO getStockDetail(String isuCd);
	

}
