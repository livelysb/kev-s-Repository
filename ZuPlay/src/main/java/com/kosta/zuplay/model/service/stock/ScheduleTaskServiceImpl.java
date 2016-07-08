package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.service.community.ChattingService;
import com.kosta.zuplay.model.service.player.EarningRateService;
import com.kosta.zuplay.model.service.player.RankService;

@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
	
	@Autowired
	private StockUpdateService stockUpdateService;
	
	@Autowired
	private EarningRateService earningRateService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private ChattingService chattingService;
	
	@Scheduled(fixedDelay=10000)
	public void testfunction() {
		chattingService.chatOnebyOne("이석범짱", "이석범짱", "Hi");
	}
	
	@Scheduled(cron="0 0/5 9-18 * * MON-FRI")
	@Override
	public void actionPer10Min() {
		System.out.println("매 5분마다 작업을 시작합니다.");
		try {
			stockUpdateService.stockPriceUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 0/10 9-18 * * MON-FRI")
	@Override
	public void actionPer20Min(){
		System.out.println("매 10분마다 작업을 시작합니다.");
		try {
			stockUpdateService.realtimePriceInsert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Scheduled(cron="0 59 9 * * MON-FRI")
	@Override
	public void actionAtNine(){
		System.out.println("AM 09:57, 작업을 시작합니다.");
		try {
			stockUpdateService.masterUpdate();
			stockUpdateService.realtimePriceReset();
			rankService.calRank("PLAYER_DAILY_RANK");
			rankService.calRank("PLAYER_SEASON_RANK");
			earningRateService.updateEarningRate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Scheduled(cron="0 0 15 * * MON-FRI")
	@Override
	public void actionAtThree(){
		System.out.println("PM 03:00, 작업을 시작합니다.");
		try {
			stockUpdateService.dailyPriceInsert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
