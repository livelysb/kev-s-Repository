package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerInfoDAO {
	/**
	 * 플레이어 리스트 가져오기(검색) throws Exception
	 */
	public List<PlayerDTO> playerInfoSelectAll(String keyword) throws Exception;
	/**
	 * 플레이어가 가지고있는 기본정보 가져오기 ( PLAYER Table ) throws Exception
	 * */
	public PlayerDTO getPlayer(String playerNickname) throws Exception;
	
	/**
	 * 모든 플레이어의 닉네임 가져오기
	 * */
	public List<String> getAllPlayerNickName() throws Exception;
	
	/**
	 * 플레이어의 사이버머니 수정하기
	 * */
	public int setPlayerMoney(Map<String, String> map) throws Exception;
	
	/**
	 * 전일 총 자산 업데이트 시키기
	 * */
	public int setPreMoney(Map<String, String> map) throws Exception;
	
	/**
	 * 플레이어 머니 가져오기
	 * */
	int getMoney(String playerNickname) throws Exception;
	
	/**
	 * 플레이어 루비 가져오기
	 */
	int getRuby(String playerNickname) throws Exception;
	
	/**
	 * 플레이어 루비 업데이트
	 */
	int updateRuby(Map<String, String> map) throws Exception;
	
	/**
	 * 수익률 업데이트 
	 * */
	int earningRateUpdate(Map<String, String> map) throws Exception;
}
