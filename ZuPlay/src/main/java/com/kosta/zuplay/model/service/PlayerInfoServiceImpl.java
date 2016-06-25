package com.kosta.zuplay.model.service;

import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

@Service
public class PlayerInfoServiceImpl implements PlayerInfoService {

	@Override
	public PlayerDTO getPlayer(String playerNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePlayerMoney(String playerNickname, double money) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePlayerStock(String playerNickname, String isuCd, int plQuantity) {
		// TODO Auto-generated method stub
		return false;
	}

}
