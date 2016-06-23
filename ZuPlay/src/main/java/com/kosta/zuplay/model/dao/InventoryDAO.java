package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface InventoryDAO {
	List<PlayerItemDTO> playerItemSelectAll(String playerNickname);
	int playerItemInsert(List<PlayerItemDTO> list);
}
