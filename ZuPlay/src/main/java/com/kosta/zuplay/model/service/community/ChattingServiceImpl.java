package com.kosta.zuplay.model.service.community;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;

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

	AtomicInteger i = new AtomicInteger(100);

	@Override
	public void chatOnebyOne(String sender, String receiver, String msg) {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		try {
			if (settingDAO.settingSelect(receiver).getPsChatting().equals("T")) {
				List<String> receivers = new ArrayList<String>();
				receivers.add(receiver);
				receivers.add(sender);
				try {
					sendDataWebSocket.sendData(sender, receivers, "oneByOne",
							new ChatMsgVO(sender, receiver, null, SimpleDateFormat.getInstance().format(new Date()),
									msg, playerInfoService.getPlayer(sender).getPlayerGender(),
									inventoryService.playerItemWorn(sender)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				List<String> receivers = new ArrayList<String>();
				receivers.add(sender);
				try {
					sendDataWebSocket.sendData(sender, receivers, "oneByOne",
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
		ChatRoomVO crv = new ChatRoomVO(roomNo, roomName, password, playersInfo, maxNum);
		map.put(roomNo, crv);
		sendDataWebSocket.sendData(sender, myself, "chatStart", crv);

		// 플레이어의 VO에 접속중인 채팅방 연결.
		PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + sender);
		if (playerVO != null) {
			playerVO.getChatRoomList().add(crv.getRoomNo());
		}
	}

	@Override
	public void chatRoomSelect(String sender, int page) {
		try {
			Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
			if (map == null)
				return;
			List<ChatRoomVO> chatRoomList = new ArrayList<ChatRoomVO>();
			int i = (page - 1) * 10 + 1;
			int start = 1;
			for (Integer roomNo : map.keySet()) {
				if (start < i) {
					start++;
					continue;
				} else {
					ChatRoomVO chatRoomVO = map.get(roomNo);
					ChatRoomVO chatRoomVO2 = new ChatRoomVO(chatRoomVO.getRoomNo(), chatRoomVO.getRoomName(), "", chatRoomVO.getPlayerList(), chatRoomVO.getMaxNum());
					
					System.out.println(chatRoomVO.getPassword());
					if(!chatRoomVO.getPassword().equals(""))
						chatRoomVO2.setPassword("T");
					else
						chatRoomVO2.setPassword("");
					chatRoomList.add(chatRoomVO2);
					if (start == i + 9)
						break;
					start++;
				}
			}
			List<String> myself = new ArrayList<String>();
			myself.add(sender);
			sendDataWebSocket.sendData(sender, myself, "chatList", chatRoomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void chatRoomJoin(String sender, int roomNo, String password) {
		Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
		ChatRoomVO crv = map.get(roomNo);

		// 조건에 맞지 않을 경우 들어오지 못함
		if (crv.getMaxNum() <= crv.getPlayerList().size() || !crv.getPassword().equals(password))
			return;

		/**
		 * 1. 방에 멤버에게 참석자의 정보를 전송
		 */
		// 방의 멤버들 (receivers)
		List<String> receivers = new ArrayList<String>();
		for (PlayerDTO playerDTO : crv.getPlayerList()) {
			receivers.add(playerDTO.getPlayerNickname());
		}
		// 나의정보 (data)
		PlayerDTO player = getPlayer(sender);
		// 전송
		sendDataWebSocket.sendData(sender, receivers, "chatIn", player);

		/**
		 * 2. 나에게 방의 정보보내기
		 */
		// 나에게 보내기(receivers)
		List<String> myself = new ArrayList<String>();
		myself.add(sender);
		// 방에 참석, 방정보 및 방 사람들의 정보(crv>(data)
		crv.getPlayerList().add(player);
		// 전송
		sendDataWebSocket.sendData(sender, myself, "chatStart", crv);

		// 플레이어의 VO에 접속중인 채팅방 연결.
		PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + sender);
		if (playerVO != null) {
			playerVO.getChatRoomList().add(crv.getRoomNo());
		}
	}

	@Override
	public void chatRoomChat(String sender, int roomNo, String msg) {
		Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
		ChatRoomVO crv = map.get(roomNo);

		// sender를 포함한 모든 참석자(receivers)
		List<String> receivers = new ArrayList<String>();
		for (PlayerDTO playerDTO : crv.getPlayerList()) {
			receivers.add(playerDTO.getPlayerNickname());
		}

		// 채팅 메시지 (data) 전송 (sendData)
		try {
			sendDataWebSocket.sendData(sender, receivers, "chatMsg", new ChatMsgVO(sender, null,
					new AtomicInteger(roomNo), SimpleDateFormat.getInstance().format(new Date()), msg,
					playerInfoService.getPlayer(sender).getPlayerGender(), inventoryService.playerItemWorn(sender)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void chatRoomOut(String sender, int roomNo) {
		Map<Integer, ChatRoomVO> map = (TreeMap<Integer, ChatRoomVO>) context.getAttribute("chatRoom");
		ChatRoomVO crv = map.get(roomNo);

		// 방 나가기
		try {
			crv.getPlayerList().remove(getPlayer(sender));
			System.out.println(sender + "나갔슴");
			System.out.println("채팅방 남은 인원수 : " + crv.getPlayerList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// receiver
		List<String> receivers = new ArrayList<String>();
		for (PlayerDTO playerDTO : crv.getPlayerList()) {
			receivers.add(playerDTO.getPlayerNickname());
		}
		sendDataWebSocket.sendData(sender, receivers, "chatOut", getPlayer(sender));

		// 플레이어의 VO에 접속 중인 채팅방 끊기
		PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + sender);
		if (playerVO != null) {
			int index = playerVO.getChatRoomList().indexOf(new Integer(roomNo));
			System.out.println(roomNo+"번방 나가기전 방 목록");
			for(int i : playerVO.getChatRoomList())
				System.out.print(i+", ");
			playerVO.getChatRoomList().remove(index);
			System.out.println(roomNo+"번방 나간 뒤 방 목록");
			for(int i : playerVO.getChatRoomList())
				System.out.print(i+", ");
		}

	}

	// 어플리케이션이 끈기기전에 닉네임 찾아서 방번호 목록 찾아서 모든 방 나가게
	public void allChatRoomOut(String playerNickname) {
		PlayerVO playerVO = (PlayerVO) context.getAttribute("#" + playerNickname);
		if (playerVO != null) {
			for (int roomNo : playerVO.getChatRoomList()) {
				chatRoomOut(playerNickname, roomNo);
			}
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
