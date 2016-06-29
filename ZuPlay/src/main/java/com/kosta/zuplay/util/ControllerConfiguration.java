package com.kosta.zuplay.util;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
 
/**
 * Description.
 *
 * @author Ha Neul Kim
 * @version 0.1
 * @since 2015-02-10
 */
@Configuration
@EnableWebSocket
@EnableWebMvc
@ComponentScan(basePackages={"com.kosta.zuplay"})
public class ControllerConfiguration extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	
	@Autowired
	  private EchoHandler echoHandler;
    
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new EchoHandler(), "/server");
    }
 
}