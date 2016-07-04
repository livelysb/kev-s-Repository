package com.kosta.zuplay.model.service.item;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public interface InventoryService {
	List<PlayerItemDTO> playerItemSelectAll(String playerNickname) throws Exception;
	boolean playerItemInsert(List<PlayerItemDTO> list) throws Exception;
}
