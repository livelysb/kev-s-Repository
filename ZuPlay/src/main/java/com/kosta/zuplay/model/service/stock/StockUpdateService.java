package com.kosta.zuplay.model.service.stock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface StockUpdateService {
	
	@Scheduled(fixedDelay=60000) //작업이 끝난지 1분 후 재시작
	public void actionPerMin();
	
	@Scheduled(cron="0 30 9 * * *") //매일 아홉시반에 작동
	public void actionAtNine();
	
	@Scheduled(cron="0 30 15 * * *") //매일 세시반에 작동
	public void actionAtFour();
}
