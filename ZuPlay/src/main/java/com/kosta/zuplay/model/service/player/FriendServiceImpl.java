package com.kosta.zuplay.model.service.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.FriendDAO;
import com.kosta.zuplay.model.dto.player.FriendDTO;
import com.kosta.zuplay.util.vo.PlayerVO;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private ServletContext application;
	@Autowired
	private SqlSession sqlSession;

	/**
	 * 접속중 친구목록 가져오기
	 */
	@Override
	public List<FriendDTO> friendSelectOnline(String playerNickname) {
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		List<FriendDTO> list = new ArrayList<FriendDTO>();
		List<FriendDTO> listA = friendDAO.friendSelectA(playerNickname);
		List<FriendDTO> listB = friendDAO.friendSelectB(playerNickname);

		List<PlayerVO> listApp = (List<PlayerVO>) application.getAttributeNames();
		List<String> listAppPlayerNickname = new ArrayList<String>();
		for (int i = 0; i < listB.size(); i++) {
			listA.add(listB.get(i));
		}
		for (int i = 0; i < listApp.size(); i++) {
			listAppPlayerNickname.add(listApp.get(i).getPlayerNickname());
		}
		for (int i = 0; i < listA.size(); i++) {
			if (listAppPlayerNickname.contains(listA.get(i).getPlayerNickname())) {
				list.add(listA.get(i));
			}
		}
		return list;
	}

	/**
	 * 친구목록 전체 조회
	 */
	@Override
	public List<FriendDTO> friendSelect(String playerNickname) {
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		List<FriendDTO> listA = friendDAO.friendSelectA(playerNickname);
		List<FriendDTO> listB = friendDAO.friendSelectB(playerNickname);
		for (int i = 0; i < listB.size(); i++) {
			listA.add(listB.get(i));
		}
		return listA;
	}

	/**
	 * 친구추가
	 */
	@Override
	public FriendDTO friendAdd(String playerNickname, String playerNickname2) {
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		FriendDTO dto = null;
		if (friendCheck(playerNickname, playerNickname2) == false) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("playerNickname", playerNickname);
			map.put("playerNickname2", playerNickname2);
			friendDAO.friendAdd(map);
			dto = friendDAO.friendCheck(map);
			System.out.println(dto);
		}
		return dto;
	}

	/**
	 * 친구여부 체크
	 */
	@Override
	public boolean friendCheck(String playerNickname, String playerNickname2) {
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerNickname", playerNickname);
		map.put("playerNickname2", playerNickname2);
		FriendDTO dtoA= friendDAO.friendCheck(map);
		map.clear();
		map.put("playerNickname2", playerNickname);
		map.put("playerNickname", playerNickname2);
		FriendDTO dtoB = friendDAO.friendCheck(map);
		System.out.println("dtoA : " + dtoA + "// dtoB : " + dtoB);
		if (dtoA==null) {
			if (dtoB==null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 친구거절/친구삭제
	 */
	@Override
	public boolean friendDel(int friendSq) {
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		int result = friendDAO.friendDel(friendSq);
		if (result != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 친구수락
	 */
	@Override
	public boolean friendAccept(int friendSq) {
		FriendDAO friendDAO = sqlSession.getMapper(FriendDAO.class);
		int result = friendDAO.friendAccept(friendSq);
		if (result != 0) {
			return true;
		}
		return false;
	}

}
