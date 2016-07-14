package com.kosta.zuplay.model.dao;

import java.util.List;
import java.util.Map;

import com.kosta.zuplay.model.dto.item.ItemDTO;

public interface ItemStoreDAO {
	/**
	 * 아이템 상점 목록 가져오기(전체) throws Exception
	 */
	List<ItemDTO> itemStoreSelectAll(Map<String, String> map) throws Exception;
	/**
	 * 아이템 상점 목록 가져오기(클래스별) throws Exception
	 */
	List<ItemDTO> itemStoreSelect(Map<String, String> map) throws Exception;
	/**
	 * 아이템 가격 가져오기
	 */
	int getPrice(String itemCode) throws Exception;
	/**
	 * 랜덤박스 리스트 가져오기
	 */
	List<ItemDTO> itemStoreRandomBoxList(Map<String, String> map) throws Exception;

}
