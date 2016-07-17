package com.kosta.zuplay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {

	@ResponseBody
	@RequestMapping(value = "searchNews", produces = "application/json;charset=UTF-8")
	public String searchNews(HttpSession session, String keyword) throws Exception {
		BufferedReader rd = null;
		HttpURLConnection conn = null;
		try {
		keyword = URLEncoder.encode(keyword,"UTF-8");
		URL url = new URL("https://testbed.koscom.co.kr/gateway/v1/uberple/news/politics%2Ceconomy%2Csociety%2Cculture%2Cworld%2Ctech%2Copinion/_search?query="+keyword+"&count=20&summary=1&clustering=0&apikey=63170644-73dc-4c51-a519-8f6aab3642d6");
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");  
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		} catch(Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}
}
