package com.kosta.zuplay.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.InventoryDAO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PlayerItemDTO> playerItemSelectAll(String playerNickname) {
		InventoryDAO dao=sqlSession.getMapper(InventoryDAO.class);
		List<PlayerItemDTO> list=dao.playerItemSelectAll(playerNickname);
		return list;
	}

	@Override
	public boolean playerItemInsert(List<PlayerItemDTO> list) {
		InventoryDAO dao=sqlSession.getMapper(InventoryDAO.class);
		int result=dao.playerItemInsert(list);
		if(result!=0){
			return true;
		}
		return false;
	}

}
