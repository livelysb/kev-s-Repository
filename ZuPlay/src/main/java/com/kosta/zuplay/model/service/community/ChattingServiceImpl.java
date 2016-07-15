package com.kosta.zuplay.model.service.community;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.SettingDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.item.InventoryService;
import com.kosta.zuplay.model.service.player.PlayerInfoService;
import com.kosta.zuplay.util.SendDataWebSocket;
import com.kosta.zuplay.util.vo.ChatMsgVO;
import com.kosta.zuplay.util.vo.ChatRoomVO;
import com.kosta.zuplay.util.vo.PlayerVO;

@Service
public class ChattingServiceImpl implements ChattingService {

	@Autowired
	SendDataWebSocket sendDataWebSocket;

	@Autowired
	InventoryService inventoryService;

	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	private ServletContext context;

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
  private HttpSession session;

	AtomicInteger i = new AtomicInteger(100);

	@Override
	public void chatOnebyOne(String sender, String receiver, String msg) {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		String msgRe="";
		for(int i = 0 ; i<msg.length();i++){
			if(msg.charAt(i)=='<'){
				msgRe+="&lt;";
			}else{
				msgRe+=msg.charAt(i);
			}
		}
		try {
			if (settingDAO.settingSelect(receiver).getPsChatting().equals("T")) {
				List<String> receivers = new ArrayList<String>();
				receivers.add(receiver);
				receivers.add(sender);
				try {
					sendDataWebSocket.sendData(receivers, "oneByOne",
							new ChatMsgVO(sender, receiver, null, SimpleDateFormat.getInstance().format(new Date()),
									msgRe, playerInfoService.getPlayer(sender).getPlayerGender(),
									inventoryService.playerItemWorn(sender)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				List<String> receivers = new ArrayList<String>();
				receivers.add(sender);
				try {
					sendDataWebSocket.sendData(receivers, "oneByOne",
							new ChatMsgVO(sender, receiver, null, null, null, null, null));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void chatRoomCreate(String sender, String roomName, String password, int maxNum) {
		String roomNameRe="";
		for(int i = 0 ; i<roomName.length();i++){
			if(roomName.charAt(i)=='<'){
				roomNameRe+="&lt;";
			}else{
				roomNameRe+=roomName.charAt(i);
			}
		}
		if (context.getAttribute("chatRoom") == null)
			context.setAttribute("chatRoom", new TreeMap<Integer, ChatRoomVO>());

		// 나에게 보내기
		List<String> myself = new ArrayList<String>();
		myself.add(sender);

		// 참석자(방 개설자) 아이템 정보 가져오기
		List<PlayerDTO> playersInfo = new ArrayList<PlayerDTO>();
		playersInfo.add(getPlayer(sender));

		Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
		int roomNo = i.getAndIncrement();
		ChatRoomVO crv = new ChatRoomVO(sender, roomNo, roomNameRe, password, playersInfo, maxNum);
		map.put(roomNo, crv);
		sendDataWebSocket.sendData(myself, "chatStart", crv);
		chatRoomSelect();

		// 플레이어의 VO에 접속중인 채팅방 연결.
		PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + sender);
		if (playerVO != null) {
			playerVO.getChatRoomList().add(crv.getRoomNo());
		}
	}

	@Override
	public void chatRoomSelect() {
		try {
			Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
			if (map == null)
				return;
			List<ChatRoomVO> chatRoomList = new ArrayList<ChatRoomVO>();
			for (Integer roomNo : map.keySet()) {

				ChatRoomVO chatRoomVO = map.get(roomNo);
				ChatRoomVO chatRoomVO2 = new ChatRoomVO(null, chatRoomVO.getRoomNo(), chatRoomVO.getRoomName(), "",
						chatRoomVO.getPlayerList(), chatRoomVO.getMaxNum());

				if (!chatRoomVO.getPassword().equals(""))
					chatRoomVO2.setPassword("T");
				else
					chatRoomVO2.setPassword("");
				
				chatRoomList.add(chatRoomVO2);
			}
			List<String> allPlayer = new ArrayList<String>();
			Enumeration<String> enumr = context.getAttributeNames();
			while (enumr.hasMoreElements()) {
				String el = enumr.nextElement();
				if (el.charAt(0) == '#') {
					String ell = el.substring(1);
					allPlayer.add(ell);
				}
			}
			sendDataWebSocket.sendData(allPlayer, "chatList", chatRoomList);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void chatRoomJoin(String sender, int roomNo, String password) {
		try {
			Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
			ChatRoomVO crv = map.get(roomNo);

			// 조건에 맞지 않을 경우 들어오지 못함
			if (crv.getMaxNum() <= crv.getPlayerList().size() || !crv.getPassword().equals(password)) {
				return;
			}

			/**
			 * 1. 방에 멤버에게 참석자의 정보를 전송
			 */
			// 방의 멤버들 (receivers)
			List<String> receivers = new ArrayList<String>();
			for (PlayerDTO playerDTO : crv.getPlayerList()) {
				receivers.add(playerDTO.getPlayerNickname());
			}
			// 방의 참에 참석, 나의정보 (변경된 채팅방 정보)
			crv.getPlayerList().add(getPlayer(sender));
			crv.setSender(sender);
			// 전송
			sendDataWebSocket.sendData(receivers, "chatIn", crv);

			/**
			 * 2. 나에게 방의 정보보내기
			 */
			// 나에게 보내기(receivers)
			List<String> myself = new ArrayList<String>();
			myself.add(sender);

			// 전송
			sendDataWebSocket.sendData(myself, "chatStart", crv);

			// 플레이어의 VO에 접속중인 채팅방 연결.
			PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + sender);
			if (playerVO != null) {
				playerVO.getChatRoomList().add(crv.getRoomNo());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		chatRoomSelect();
	}

	@Override
	public void chatRoomChat(String sender, int roomNo, String msg) {
		String msgRe="";
		for(int i = 0 ; i<msg.length();i++){
			if(msg.charAt(i)=='<'){
				msgRe+="&lt;";
			}else{
				msgRe+=msg.charAt(i);
			}
		}
		Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
		ChatRoomVO crv = map.get(roomNo);

		// sender를 포함한 모든 참석자(receivers)
		List<String> receivers = new ArrayList<String>();
		for (PlayerDTO playerDTO : crv.getPlayerList()) {
			receivers.add(playerDTO.getPlayerNickname());
		}

		// 채팅 메시지 (data) 전송 (sendData)
		try {
			sendDataWebSocket.sendData(receivers, "chatMsg", new ChatMsgVO(sender, null,
					new AtomicInteger(roomNo), SimpleDateFormat.getInstance().format(new Date()), msgRe,
					playerInfoService.getPlayer(sender).getPlayerGender(), inventoryService.playerItemWorn(sender)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		chatRoomSelect();

	}

	@Override
	public void chatRoomOut(String sender, int roomNo, boolean allOut) {
		Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
		ChatRoomVO crv = map.get(roomNo);

		System.out.println(roomNo + "번방의 현재 인원수 : " + crv.getPlayerList().size() + "명");
		// 방 나가기
		try {
			PlayerDTO playerDTO = null;
			for (PlayerDTO playerDTO2 : crv.getPlayerList()) {
				if (playerDTO2.getPlayerNickname().equals(sender)) {
					playerDTO = playerDTO2;
					break;
				}
			}
			crv.getPlayerList().remove(playerDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("방의 잔여 인원수 : " + crv.getPlayerList().size() + "명");
		if (crv.getPlayerList().size() == 0) {
			map.remove(roomNo);
		} else {
			// receiver
			List<String> receivers = new ArrayList<String>();
			for (PlayerDTO playerDTO : crv.getPlayerList()) {
				receivers.add(playerDTO.getPlayerNickname());
				System.out.println("플레이어 닉네임 : " + playerDTO.getPlayerNickname());
			}
			crv.setSender(sender);
			sendDataWebSocket.sendData(receivers, "chatOut", crv);
		}

		if (!allOut) {
			// 플레이어의 VO에 접속 중인 채팅방 끊기
			PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + sender);
			if (playerVO != null) {
				int index = playerVO.getChatRoomList().indexOf(new Integer(roomNo));
				System.out.println(roomNo + "번방 나가기전 방 목록");
				for (int i : playerVO.getChatRoomList())
					System.out.print(i + ", ");
				System.out.println();
				playerVO.getChatRoomList().remove(index);
				System.out.println(roomNo + "번방 나간 뒤 방 목록");
				for (int i : playerVO.getChatRoomList())
					System.out.print(i + ", ");
				System.out.println();
			}
		}
		chatRoomSelect();

	}

	// 어플리케이션이 끈기기전에 닉네임 찾아서 방번호 목록 찾아서 모든 방 나가게
	public void allChatRoomOut(String playerNickname) {
		PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + playerNickname);
		if (playerVO != null) {
			for (int roomNo : playerVO.getChatRoomList()) {
				chatRoomOut(playerNickname, roomNo, true);
			}
			playerVO.setChatRoomList(null);
		}
	}

	public PlayerDTO getPlayer(String playerNickname) {
		try {
			PlayerDTO playerDTO = playerInfoService.getPlayer(playerNickname);
			playerDTO.setPlayerItemDTO(inventoryService.playerItemWorn(playerNickname));
			return playerDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
