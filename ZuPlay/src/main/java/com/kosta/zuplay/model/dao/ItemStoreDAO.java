package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.item.ItemDTO;

public interface ItemStoreDAO {
	List<ItemDTO> itemStoreSelect(Map<String, String> map);
}
