package net.fnsco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.core.utils.DateUtils;
import net.fnsco.risk.service.ability.MercPayAbilityService;

@EnableScheduling
public class TimerConfig {

	@Autowired
	private MercPayAbilityService mercPayAbilityService;

	/**
	 * spring boot 定时任务 2.扣款时间：早上 9点 下午 1点 下午5点 ，若第一次扣款没有成功，第二次继续请求，第二次没有成功，第三次继续请求。
	 */
	// @Scheduled(cron = "0 */5 * * * ?")
	public void reportCurrentTime() {
		System.out.println("定时任务执行");
	}
	
	/**
	 * 定时统计还款能力数据--每月一号凌晨一点执行
	 */ 
	@Scheduled(cron = "0 0 1 1 * ?")
//	@Scheduled(cron = "0 * * * * ?")
	public void countRepaymentAbilityTimer() {
		mercPayAbilityService.countRepaymentAbility(DateUtils.getMouthStartTime1(-1), DateUtils.getMouthStartTime1(0));
	}
}
