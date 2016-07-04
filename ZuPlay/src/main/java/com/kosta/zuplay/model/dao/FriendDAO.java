package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.player.FriendDTO;

public interface FriendDAO {
	/**
	 * 친구조회A
	 */
	List<FriendDTO> friendSelectA(String playerNickname) throws Exception;

	/**
	 * 친구조회B
	 */
	List<FriendDTO> friendSelectB(String playerNickname) throws Exception;

	/**
	 * 친구체크
	 */
	FriendDTO friendCheck(Map<String, String> map) throws Exception;

	/**
	 * 친구신청
	 */
	int friendAdd(Map<String, String> map) throws Exception;

	/**
	 * 친구삭제
	 */
	int friendDel(int friendSq) throws Exception;

	/**
	 * 친구수락
	 */
	int friendAccept(int friendSq) throws Exception;
}
