
package com.kosta.zuplay.model.service.community;

public interface ChattingService {
	
	/**
	 * 1:1 채팅하기
	 * */
	void chatOnebyOne(String sender, String receiver, String msg);
	
	
	/**
	 * 채팅방 만들기
	 * */
	void chatRoomCreate(String sender, String roomName, String password,int maxNum);
	
	/**
	 * 채팅방 참가
	 * */
	void chatRoomJoin(String sender, int roomNo, String password);
	
			
	/**
	 * 대화 진행하기
	 * */
	void chatRoomChat(String sender, int roomNo, String msg);
	
	
	/**
	 * 채팅방 나가기
	 * */
	void chatRoomOut(String sender, int roomNo);
	
	/**
	 * 모든 채팅방 나가기
	 */
	void allChatRoomOut(String sender);
	
}
