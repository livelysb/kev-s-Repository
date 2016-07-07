package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
	
	@Scheduled(cron="0 0/5 9-18 * * MON-FRI")
	@Override
	public void actionPer10Min() {
		System.out.println("10분마다 작업을 시작합니다.");
		try {
			stockUpdateService.stockPriceUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 0/10 9-18 * * MON-FRI")
	@Override
	public void actionPer20Min(){
		System.out.println("20분마다 작업을 시작합니다.");
		try {
			stockUpdateService.realtimePriceInsert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Scheduled(cron="0 57 9 * * *")
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

	
	@Scheduled(cron="0 0 15 * * *")
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
