package com.kosta.zuplay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DictionaryController {
	
	@ResponseBody
	@RequestMapping(value = "searchFinancialTerm", produces = "application/json;charset=UTF-8")
	public String getStockList(String term) {
		URL url = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			url = new URL("http://api.seibro.or.kr/openapi/service/FnTermSvc/getFinancialTermMeaning?ServiceKey=pZqmiyxvoN2Gs3%2BDlr4F0vaxjytfFV3%2FIlnklIFqPoyNZvux7gwoAycn7VfKYPbdcZaFzlA2EWcTdIKRBM%2F4jw%3D%3D&term="+term+"&pageNo=1&startPage=1&numOfRows=100&pageSize=100");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String s = null;
			while((s = br.readLine()) != null)
				sb.append(s);
				
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
