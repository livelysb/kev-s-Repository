package com.kosta.zuplay.model.service.stock;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.zuplay.model.service.PlayerInfo;

@Service
public class StockTradeServiceImpl implements StockTradeService {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PlayerInfo playerInfo;
	
	@Autowired
	private StockInfo stockInfo;
	/**
	 * 주식 판매
	 * */
	@Transactional
	@Override
	public boolean SellStock(String playerNickname, String isuCd, int plQuantity) {
		
		return false;
	}

	
	/**
	 * 주식 구매
	 * */
	@Transactional
	@Override
	public boolean BuyStock(String playerNickname, String isuCd, int plQuantity) {
		int playerMoney = playerInfo.getPlayer(playerNickname).getPlayerMoney();
		int price = (int)stockInfo.getPrice(isuCd).getTrdPrc();
		int totalPrice = price*plQuantity;
		if(playerMoney >= totalPrice) {
			// 돈 깍고, 주식 추가해주기
			
			return true;
		} else {
			return false;
		}
	}

}
