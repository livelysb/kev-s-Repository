package com.kosta.zuplay.model.service;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.ItemStoreDAO;

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
		List<Integer> list=itemStoreDAO.getItemIndex(playerNickname);
		System.out.println(list);
		for(int i=0;i<list.size();i++){
			
			System.out.println(list.get(i)+" / "+i+1);
			if(list.get(i)!=i+1){
				return i+1;
			}
			
		}
		if(list.size()<21){
			return list.size()+1;
		}
		return 0;
	}
	
	public String currentDate() {
		 Calendar oCalendar = Calendar.getInstance( );
		 // 현재 날짜/시간 등의 각종 정보 얻기
		 String currentDate = "";
		 currentDate = Integer.toString(oCalendar.get(Calendar.YEAR));
		 currentDate += (oCalendar.get(Calendar.MONTH) + 1)>9 ? ""+Integer.toString(oCalendar.get(Calendar.MONTH) + 1) : '0'+Integer.toString(oCalendar.get(Calendar.MONTH) + 1);
		 currentDate += '0'+Integer.toString(oCalendar.get(Calendar.DAY_OF_MONTH));
		 return currentDate;
		}

}
