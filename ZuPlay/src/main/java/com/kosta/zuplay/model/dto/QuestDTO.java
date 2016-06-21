package com.kosta.zuplay.model.dto;

public class QuestDTO {
	String questCode;
	String questClass;
	String questName;
	String questContent;
	int questGoal;
	int questReward;
	String itemCode;
	
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
