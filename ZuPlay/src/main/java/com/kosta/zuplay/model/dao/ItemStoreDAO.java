package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.item.ItemDTO;

public interface ItemStoreDAO {
	List<ItemDTO> itemStoreSelect(Map<String, String> map);
	int getRuby(String playerNickname);
	int getPrice(String itemCode);
	int payRuby(String playerNickname,String price);
	int itemStoreBuy(String playerNickname,String itemCode,String quantity);
	int itemDelete(String piSq);
	int payBackRuby(String playerNickname,String payBackPrice);
}
