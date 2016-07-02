package com.kosta.zuplay.model.service.player;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public interface PlayerInfoService {
	/**
	 * 플레이어 리스트 가져오기
	 */
	public List<PlayerDTO> playerInfoSelectAll(String keyword) throws Exception;
	/**
	 * 플레이어 정보 가져오기
	 * */
	public PlayerDTO getPlayer(String playerNickname) throws Exception;

	/**
	 * 모든 플레이어의 닉네임 가져오기
	 * */
	public List<String> getAllPlayerNickName() throws Exception;
	
	/**
	 * 플레이어의 사이버머니 수정하기
	 * */
	public boolean setPlayerMoney(String playerNickname, int playerMoney) throws Exception;
	
	/**
	 * 주식을 포함한 현재 자산 구하기
	 * */
	public int getTotalMoney(String playerNickname) throws Exception;
	
	/**
	 * 접속자의 최종 접속시간을 확인하여 하루에 한번씩 1000루비 주기 
	 * */
	
	
	/**
	 * 플레이어 머니 가져오기
	 * */
	public int getMoney(String playerNickname) throws Exception;
	
	/**
	 * 플레이어 루비 가져오기
	 */
	public int getRuby(String playerNickname) throws Exception;
	/**
	 * 플레이어 루비 업데이트
	 */
	public boolean updateRuby(String playerNickname,int ruby) throws Exception;

}
