package com.kosta.zuplay.model.service;

import java.util.List;

import com.kosta.zuplay.model.dto.item.ItemDTO;

public interface ItemStoreService {
	List<ItemDTO> itemStoreSelect(String playerNickname,String itemClass,int page);
	boolean itemStoreBuy(String playerNickname,ItemDTO itemDTO, int quantity);
}
