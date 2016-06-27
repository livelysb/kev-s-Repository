package com.kosta.zuplay.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.ItemStoreDAO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

@Service
public class UtilServiceImpl implements UtilService {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 아이템창 빈 인덱스 찾기(최대 20개)
	 * return 1~20 번 인덱스 / 0 = 자리 없음 (인벤토리 가득참)
	 */
	@Override
	public int indexSearch(String playerNickname) {
		ItemStoreDAO itemStoreDAO=sqlSession.getMapper(ItemStoreDAO.class);
		int[] indexPoolList={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		List<Integer> list=itemStoreDAO.getItemIndex(playerNickname);
		System.out.println(list);
		for(int i=0;i<list.size();i++){
			
			if(list.get(i)!=indexPoolList[i]){
				return indexPoolList[i];
			}
			if(list.size()<21){
				return list.size()+1;
			}
		}
		return 0;
	}

}
