package com.kosta.zuplay.model.service.stock;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dto.player.PlayerListsDTO;

@Service
public class PlayerStockInfoImpl implements PlayerStockInfo {

	@Override
	public List<PlayerListsDTO> getPlayerStocks(String playerNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerListsDTO getPlayerStock(String playerNickname, String isuCd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEarningRate(String playerNickname) {
		// TODO Auto-generated method stub
		return 0;
	}

}
