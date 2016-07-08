package com.kosta.zuplay.model.service.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.service.item.InventoryService;
import com.kosta.zuplay.model.service.player.PlayerInfoService;
import com.kosta.zuplay.util.SendDataWebSocket;
import com.kosta.zuplay.util.vo.ChatMsgVO;

@Service
public class ChattingServiceImpl implements ChattingService {

	@Autowired
	SendDataWebSocket sendDataWebSocket;

	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	PlayerInfoService playerInfoService;

	@Override
	public void chatOnebyOne(String sender, String receiver, String msg) {
		List<String> receivers = new ArrayList<String>();
		receivers.add(receiver);
		try {
			sendDataWebSocket.sendData(sender, receivers, "oneByOne", new ChatMsgVO(sender, null, null, new Date().toString(), msg,
					playerInfoService.getPlayer(sender).getPlayerGender(), inventoryService.playerItemWorn(sender)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
