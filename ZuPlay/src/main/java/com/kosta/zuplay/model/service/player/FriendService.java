package com.kosta.zuplay.model.service.player;

import java.util.List;

import com.kosta.zuplay.model.dto.player.FriendDTO;
import com.kosta.zuplay.model.dto.player.FriendVO;

public interface FriendService {
	/**
	 * 접속중 친구명 조회
	 */
	List<String> friendSelectOnlyNickname(String playerNickname);
//	/**
//	 * 접속중 친구목록조회
//	 */
//	List<FriendDTO> friendSelectOnline(String playerNickname);
	/**
	 * 친구목록 전체 조회
	 */
	List<FriendVO> friendSelect(String playerNickname);
	/**
	 * 친구여부 체크
	 */
	boolean friendCheck(String playerNickname,String playerNickname2);
	/**
	 * 친구신청
	 */
	FriendDTO friendAdd(String playerNickname,String playerNickname2);
	/**
	 * 친구삭제,거절
	 */
	boolean friendDel(int friendSq);
	/**
	 * 친구수락
	 */
	boolean friendAccept(int friendSq);

}
