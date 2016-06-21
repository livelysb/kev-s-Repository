package com.kosta.zuplay.model.dto.item;

public class ItemDTO {
	String itemCode;	//?��?��?��코드
	String itemName;	//?��?��?��?���?
	int itemPrice;		//?��?��?���?�?
	String itemClass;	//?��?��?��구분
	String itemGrade;	//?��?��?��?���?
	String itemImg;		//?��?��?��?��미�?
	
	public ItemDTO() {}
	public ItemDTO(String itemCode, String itemName, int itemPrice, String itemClass, String itemGrade,
			String itemImg) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemClass = itemClass;
		this.itemGrade = itemGrade;
		this.itemImg = itemImg;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemClass() {
		return itemClass;
	}
	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	public String getItemGrade() {
		return itemGrade;
	}
	public void setItemGrade(String itemGrade) {
		this.itemGrade = itemGrade;
	}
	public String getItemImg() {
		return itemImg;
	}
	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "ItemDTO [itemCode=" + itemCode + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemClass="
				+ itemClass + ", itemGrade=" + itemGrade + ", itemImg=" + itemImg + "]";
	}
	
	
	
}
