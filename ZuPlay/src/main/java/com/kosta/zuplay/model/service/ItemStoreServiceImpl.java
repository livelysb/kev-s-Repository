package com.kosta.zuplay.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.ItemStoreDAO;
import com.kosta.zuplay.model.dao.LoginDAO;
import com.kosta.zuplay.model.dto.item.ItemDTO;

@Service
public class ItemStoreServiceImpl implements ItemStoreService {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<ItemDTO> itemStoreSelect(String playerNickname, String itemClass, int page) {
		ItemStoreDAO itemStoreDAO=sqlSession.getMapper(ItemStoreDAO.class);
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		Map<String, String> map=new HashMap<>();
		map.put("playerGender", loginDAO.getGender(playerNickname));
		map.put("itemClass", itemClass);
		map.put("startNo", 1+((page-1)*8)+"");
		map.put("endNo", page*8+"");
		List<ItemDTO> list=itemStoreDAO.itemStoreSelect(map);
		System.out.println("[ LOG ] list = "+list);
		return list;
	}

}
