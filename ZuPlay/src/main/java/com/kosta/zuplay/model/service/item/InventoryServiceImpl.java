package com.kosta.zuplay.model.service.item;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.PlayerItemDAO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PlayerItemDTO> playerItemSelectAll(String playerNickname) throws Exception {
		PlayerItemDAO dao=sqlSession.getMapper(PlayerItemDAO.class);
		List<PlayerItemDTO> list=dao.playerItemSelectAll(playerNickname);
		return list;
	}

	@Override
	public boolean playerItemInsert(List<PlayerItemDTO> list) throws Exception {
		PlayerItemDAO dao=sqlSession.getMapper(PlayerItemDAO.class);
		int result=dao.playerItemInsert(list);
		if(result!=0){
			return true;
		}
		return false;
	}

}
