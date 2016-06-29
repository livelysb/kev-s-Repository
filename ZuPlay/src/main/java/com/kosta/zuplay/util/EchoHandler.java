package com.kosta.zuplay.util;
 
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
/**
 * Description.
 *
 * @author Ha Neul Kim
 * @version 0.1
 * @since 2015-02-10
 */
public class EchoHandler extends TextWebSocketHandler {
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("ECHO : " + message.getPayload()));
    }
}