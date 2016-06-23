package com.kosta.zuplay.model.service;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface InventoryService {
	List<PlayerItemDTO> playerItemSelectAll(String playerNickname);
	boolean playerItemInsert(List<PlayerItemDTO> list);
}
