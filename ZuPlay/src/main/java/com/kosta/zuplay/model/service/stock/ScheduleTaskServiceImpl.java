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
	
	@Scheduled(fixedDelay=10*60*1000)
	@Override
	public void actionPer10Min() throws Exception {
		System.out.println("10분마다 작업을 시작합니다.");
		stockUpdateService.stockPriceUpdate();
	}
	
	@Scheduled(fixedDelay=20*60*1000)
	@Override
	public void actionPer20Min() throws Exception{
		System.out.println("20분마다 작업을 시작합니다.");
		stockUpdateService.realtimePriceInsert();
	}
	

	@Scheduled(cron="0 0 9 * * *")
	@Override
	public void actionAtNine() throws Exception{
		System.out.println("AM 09:00, 작업을 시작합니다.");
		stockUpdateService.masterUpdate();
		stockUpdateService.realtimePriceReset();
		rankService.calRank("PLAYER_DAILY_RANK");
		rankService.calRank("PLAYER_SEASON_RANK");
		earningRateService.updateEarningRate();
	}

	
	@Scheduled(cron="0 0 15 * * *")
	@Override
	public void actionAtThree() throws Exception{
		System.out.println("PM 03:00, 작업을 시작합니다.");
		stockUpdateService.dailyPriceInsert();
	}
	
}
