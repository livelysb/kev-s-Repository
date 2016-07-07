package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.player.FriendDTO;

public interface FriendDAO {
	/**
	 * 친구명 조회A
	 */
	List<String> friendSelectOnlyNicknameA(String playerNickname);
	/**
	 * 친구명 조회B
	 */
	List<String> friendSelectOnlyNicknameB(String playerNickname);
	/**
	 * 친구조회A
	 */
	List<FriendDTO> friendSelectA(String playerNickname);

	/**
	 * 친구조회B
	 */
	List<FriendDTO> friendSelectB(String playerNickname);

	/**
	 * 친구체크
	 */
	FriendDTO friendCheck(Map<String, String> map);

	/**
	 * 친구신청
	 */
	int friendAdd(Map<String, String> map);

	/**
	 * 친구삭제
	 */
	int friendDel(int friendSq);

	/**
	 * 친구수락
	 */
	int friendAccept(int friendSq);
}
