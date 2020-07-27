package com.punch.task;

import static com.punch.entity.Tables.OA_USER;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jooq.DSLContext;
import org.minbox.framework.api.boot.plugin.quartz.ApiBootQuartzService;
import org.minbox.framework.api.boot.plugin.quartz.wrapper.ApiBootJobParamWrapper;
import org.minbox.framework.api.boot.plugin.quartz.wrapper.support.ApiBootOnceJobWrapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;
import com.punch.common.enums.EnableType;
import com.punch.common.enums.PunchType;
import com.punch.common.util.ClockUtil;
import com.punch.common.util.OkHttpUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiachao
 * @date 2020/07/22 13:17
 */
@Slf4j
public class JobTask extends QuartzJobBean {

	@Resource
	private DSLContext create;

	@Resource
	private ApiBootQuartzService apiBootQuartzService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String workDay = "0";
		log.info("定时任务Job Key ： {}", context.getJobDetail().getKey());
		log.info("定时任务执行时所携带的参数：{}", JSON.toJSONString(context.getJobDetail().getJobDataMap()));
		String result = OkHttpUtil.get("http://tool.bitefu.net/jiari?d=today");
		if (!result.equals(workDay)) {
			return;
		}
		List<Map<String, Object>> list = create.select().from(OA_USER)
				.where(OA_USER.ENABLE.eq(EnableType.YES.getStatus())).fetchMaps();
		if (!list.isEmpty()) {
			for (Map<String, Object> map : list) {
				if (null == map.get("push_id")) {
					return;
				}
				// 上班打卡
				map.put("punchTask", PunchType.PUNCH_ON);
				apiBootQuartzService.newJob(ApiBootOnceJobWrapper.Context().jobClass(PunchTask.class)
						.param(ApiBootJobParamWrapper.wrapper().put("map", JSON.toJSONString(map)))
						.startAtTime(ClockUtil.getPunchOnTime()).wrapper());
				// 下班打卡
				map.put("punchTask", PunchType.PUNCH_OFF);
				apiBootQuartzService.newJob(ApiBootOnceJobWrapper.Context().jobClass(PunchTask.class)
						.param(ApiBootJobParamWrapper.wrapper().put("map", JSON.toJSONString(map)))
						.startAtTime(ClockUtil.getPunchOffTime()).wrapper());
			}
		}
	}
}
