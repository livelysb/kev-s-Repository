package com.kosta.zuplay.model.dto;

public class QuestDTO {
	String questCode;		//퀘스트코드
	String questClass;		//퀘스트분류
	String questName;		//퀘스트명
	String questContent;	//퀘스트내용
	int questGoal;			//목표수
	int questReward;		//퀘스트보상금 혹은 수량
	String itemCode;		//아이템코드
	
	public QuestDTO() {}
	public QuestDTO(String questCode, String questClass, String questName, String questContent, int questGoal,
			int questReward, String itemCode) {
		super();
		this.questCode = questCode;
		this.questClass = questClass;
		this.questName = questName;
		this.questContent = questContent;
		this.questGoal = questGoal;
		this.questReward = questReward;
		this.itemCode = itemCode;
	}
	public String getQuestCode() {
		return questCode;
	}
	public void setQuestCode(String questCode) {
		this.questCode = questCode;
	}
	public String getQuestClass() {
		return questClass;
	}
	public void setQuestClass(String questClass) {
		this.questClass = questClass;
	}
	public String getQuestName() {
		return questName;
	}
	public void setQuestName(String questName) {
		this.questName = questName;
	}
	public String getQuestContent() {
		return questContent;
	}
	public void setQuestContent(String questContent) {
		this.questContent = questContent;
	}
	public int getQuestGoal() {
		return questGoal;
	}
	public void setQuestGoal(int questGoal) {
		this.questGoal = questGoal;
	}
	public int getQuestReward() {
		return questReward;
	}
	public void setQuestReward(int questReward) {
		this.questReward = questReward;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	@Override
	public String toString() {
		return "QuestDTO [questCode=" + questCode + ", questClass=" + questClass + ", questName=" + questName
				+ ", questContent=" + questContent + ", questGoal=" + questGoal + ", questReward=" + questReward
				+ ", itemCode=" + itemCode + "]";
	}
	
	
}
