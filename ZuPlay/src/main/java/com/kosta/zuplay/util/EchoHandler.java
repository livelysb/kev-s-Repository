package com.kosta.zuplay.util;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 */
@Component
public class EchoHandler extends TextWebSocketHandler {
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("ECHO : " + message.getPayload()));
        String messageS=message.toString();
        System.out.println("message= " + message);
        System.out.println("messageS= " + messageS);

    }
}