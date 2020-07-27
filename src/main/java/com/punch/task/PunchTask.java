package com.punch.task;

import javax.annotation.Resource;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.punch.common.enums.PunchType;
import com.punch.service.IPunchService;

/**
 * 打卡
 *
 * @author xiachao
 * @date 2020/07/22 15:44
 */
public class PunchTask extends QuartzJobBean {

	@Resource
	private IPunchService punchService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Object o = jobDataMap.get("map");
		JSONObject jsonObject = JSON.parseObject(o.toString());
		PunchType punchType = (PunchType) jsonObject.get("punchTask");
		punchService.punch(jsonObject.getString("login_id"), jsonObject.getString("push_id"), punchType);
	}
}
