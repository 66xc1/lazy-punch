package com.punch.task;

import static com.punch.entity.Tables.OA_USER;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.alibaba.fastjson.JSONObject;
import com.punch.common.enums.EnableType;
import com.punch.common.enums.PunchType;
import com.punch.common.util.ClockUtil;
import com.punch.common.util.OkHttpUtil;
import com.punch.service.IPunchService;

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

	@Resource
	private IPunchService punchService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 非工作日不打卡
		if (workDay() != 0) {
			return;
		}
		List<Map<String, Object>> list = create.select().from(OA_USER)
				.where(OA_USER.ENABLE.eq(EnableType.YES.getStatus())).fetchMaps();
		if (!list.isEmpty()) {
			for (Map<String, Object> map : list) {
				if (null == map.get("push_id")) {
					return;
				}
				if (LocalTime.now().isBefore(LocalTime.of(7, 30))) {
					// 上班打卡
					PunchType punchOn = PunchType.PUNCH_ON;
					map.put("punchTask", punchOn);
					apiBootQuartzService.newJob(ApiBootOnceJobWrapper.Context().jobClass(PunchTask.class)
							.param(ApiBootJobParamWrapper.wrapper().put("map", JSON.toJSONString(map)))
							.startAtTime(ClockUtil.getPunchOnTime()).wrapper());
				}

				if (LocalTime.now().isBefore(LocalTime.of(16, 30))) {
					// 下班打卡
					map.put("punchTask", PunchType.PUNCH_OFF);
					apiBootQuartzService.newJob(ApiBootOnceJobWrapper.Context().jobClass(PunchTask.class)
							.param(ApiBootJobParamWrapper.wrapper().put("map", JSON.toJSONString(map)))
							.startAtTime(ClockUtil.getPunchOffTime()).wrapper());
				}
			}
		}
	}

	/**
	 * 判断是否工作日
	 *
	 * @return int 0工作日 1非工作日
	 */
	private int workDay() {
		boolean flag = false;
		int workDay = 1;
		int max = 10;
		String url = "http://api.tianapi.com/txapi/jiejiari/index?key=13e7f466941f1b7661d8c01fd3fbcdf8&date="
				+ LocalDate.now().toString();
		for (int i = 0; i < max; i++) {
			String result = OkHttpUtil.get(url);
			if (result != null) {
				JSONObject jsonObject = JSON.parseObject(result);
				if (200 == jsonObject.getInteger("code")) {
					flag = true;
					workDay = jsonObject.getJSONArray("newslist").getJSONObject(0).getInteger("isnotwork");
					break;
				}
			}
		}
		if (!flag) {
			punchService.sendAllMessage("打卡服务异常，请手动打卡！！！");
		}
		return workDay;
	}
}
