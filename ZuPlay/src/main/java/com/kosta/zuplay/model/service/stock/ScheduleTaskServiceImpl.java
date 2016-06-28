package com.kosta.zuplay.model.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.service.player.EarningRateService;

@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
	
	@Autowired
	private StockUpdateServiceImpl stockUpdateService;
	
	@Autowired
	private EarningRateService earningRateService;
	
	@Scheduled(fixedDelay=10*60*1000)
	@Override
	public void actionPer10Min() {
		System.out.println("10분마다 작업을 시작합니다.");
		stockUpdateService.stockPriceUpdate();
	}
	
	@Scheduled(fixedDelay=20*60*1000)
	@Override
	public void actionPer20Min() {
		System.out.println("20분마다 작업을 시작합니다.");
		stockUpdateService.realtimePriceInsert();
	}
	

	@Scheduled(cron="0 0 9 * * *")
	@Override
	public void actionAtNine() {
		System.out.println("AM 09:00, 작업을 시작합니다.");
		stockUpdateService.masterUpdate();
		stockUpdateService.realtimePriceReset();
		earningRateService.updateEarningRate();
	}

	
	@Scheduled(cron="0 0 15 * * *")
	@Override
	public void actionAtThree() {
		System.out.println("PM 03:00, 작업을 시작합니다.");
		stockUpdateService.dailyPriceInsert();
	}
	
}
