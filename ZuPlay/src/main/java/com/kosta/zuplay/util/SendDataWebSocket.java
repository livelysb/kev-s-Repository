package com.kosta.zuplay.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

import com.google.gson.Gson;
import com.kosta.zuplay.util.vo.PlayerVO;

@Controller
public class SendDataWebSocket {

	@Autowired
	private ServletContext context;
	
	public void sendData(List<String> receivers, String type, Object object) {
		List<PlayerVO> receiverList = new ArrayList<PlayerVO>();
		for (String receiver : receivers) {
			PlayerVO player = (PlayerVO) context.getAttribute("#" + receiver);
			if (player != null)
				receiverList.add(player);
		}
		System.out.println(receiverList.size());
		String json = "{\"type\":\"" + type + "\",\"data\":" + new Gson().toJson(object) + "}";
		System.out.println(json);
		for (PlayerVO player : receiverList) {
			try {
				player.getSession().sendMessage(new TextMessage(json));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
