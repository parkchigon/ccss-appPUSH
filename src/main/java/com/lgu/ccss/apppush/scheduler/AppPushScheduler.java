package com.lgu.ccss.apppush.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lgu.ccss.apppush.service.push.AppPushService;
import com.lgu.ccss.apppush.service.status.StatusInitService;

@Service
public class AppPushScheduler {
	private final Logger logger = LoggerFactory.getLogger(AppPushScheduler.class);
	
	@Autowired
	private AppPushService appPushService;
	@Autowired
	private StatusInitService statusInitService;
	
	boolean isFirst = true;
	
	@Scheduled(fixedRateString  = "${delay.time}")
	public void startWork() {
		try {
			logger.info("###### START APP PUSH DAEMON #####");
			if(isFirst) {
				logger.debug("FirstTime!! APP PUSH Data Status Init!");
				statusInitService.doTask();
				logger.debug("APP PUSH Message Data Init! Success!!");
				isFirst = false;
			}
			appPushService.doTask();

		} catch (Exception e) {
			logger.error("{}", e);

		} finally {
			logger.info("###### END APP PUSH DAEMON #####");
		}
	}
}
