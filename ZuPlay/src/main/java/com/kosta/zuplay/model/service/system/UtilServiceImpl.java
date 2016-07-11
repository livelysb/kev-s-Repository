package com.kosta.zuplay.model.service.system;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.ItemStoreDAO;
import com.kosta.zuplay.model.dao.PlayerInfoDAO;
import com.kosta.zuplay.model.dao.PlayerItemDAO;

@Service
public class UtilServiceImpl implements UtilService {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 아이템창 빈 인덱스 찾기(최대 20개) return 11~30 번 인덱스 / 0 = 자리 없음 (인벤토리 가득참)
	 */
	@Override
	public int indexSearch(String playerNickname) throws Exception {
		PlayerItemDAO playerItemDAO = sqlSession.getMapper(PlayerItemDAO.class);
		List<Integer> list = playerItemDAO.getItemIndex(playerNickname);
		System.out.println(list);
		if (list.size() >= 20) {
			return 0;
		}
		if (list.size() == 0) {
			return 11;
		} else {
			for (int i = 0; i < list.size(); i++) {

				if (list.get(i) != i + 11) {
					return i + 11;
				}

			}
		}

		return list.size() + 11;
	}

	public String currentDate() throws Exception {
		Calendar oCalendar = Calendar.getInstance();
		// 현재 날짜/시간 등의 각종 정보 얻기
		String currentDate = "";
		currentDate = Integer.toString(oCalendar.get(Calendar.YEAR));
		currentDate += (oCalendar.get(Calendar.MONTH) + 1) > 9
				? "" + Integer.toString(oCalendar.get(Calendar.MONTH) + 1)
				: '0' + Integer.toString(oCalendar.get(Calendar.MONTH) + 1);
		if (oCalendar.get(Calendar.DAY_OF_MONTH) < 10) {
			currentDate += '0' + Integer.toString(oCalendar.get(Calendar.DAY_OF_MONTH));
		} else {
			currentDate += Integer.toString(oCalendar.get(Calendar.DAY_OF_MONTH));
		}
		return currentDate;
	}

}
