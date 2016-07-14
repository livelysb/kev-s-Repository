package com.kosta.zuplay.model.service.item;

import java.util.List;

import com.kosta.zuplay.model.dto.item.ItemDTO;

public interface ItemStoreService {
	List<ItemDTO> itemStoreSelect(String playerNickname,String itemClass,int page) throws Exception;
	String itemStoreBuy(String playerNickname,ItemDTO itemDTO, int quantity) throws Exception;
	boolean itemStoreSell(String playerNickname,int piSq,String itemCode) throws Exception;
}
