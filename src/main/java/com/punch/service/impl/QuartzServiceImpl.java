package com.punch.service.impl;

import static com.punch.entity.Tables.SYS_QUARTZ;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jooq.DSLContext;
import org.minbox.framework.api.boot.plugin.quartz.ApiBootQuartzService;
import org.minbox.framework.api.boot.plugin.quartz.wrapper.support.ApiBootCronJobWrapper;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.punch.common.entity.Result;
import com.punch.entity.tables.records.SysQuartzRecord;
import com.punch.model.QuartzDto;
import com.punch.model.UpdQuartzDto;
import com.punch.service.IQuartzService;

/**
 * @author xiachao
 * @date 2020/07/22 14:38
 */
@Service
public class QuartzServiceImpl implements IQuartzService {

	@Resource
	private DSLContext create;

	@Resource
	private ApiBootQuartzService apiBootQuartzService;

	/**
	 * 查询定时任务
	 *
	 * @return result
	 */
	@Override
	public Result<List<Map<String, Object>>> getQuartzList() {
		return Result.success(create.select().from(SYS_QUARTZ).orderBy(SYS_QUARTZ.CREATE_TIME.desc()).fetchMaps());
	}

	/**
	 * 新增定时任务
	 *
	 * @param quartzDto dto
	 * @return result
	 */
	@Override
	public Result<String> addQuartz(QuartzDto quartzDto) {
		return initQuartz(quartzDto);
	}

	/**
	 * 删除定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	@Override
	public Result<String> delQuartz(String jobKey) {
		apiBootQuartzService.deleteJob(jobKey);
		create.delete(SYS_QUARTZ).where(SYS_QUARTZ.JOB_KEY.eq(jobKey)).execute();
		return Result.success();
	}

	/**
	 * 修改定时任务
	 *
	 * @param updQuartzDto updQuartz
	 * @return result
	 */
	@Override
	public Result<String> updQuartz(UpdQuartzDto updQuartzDto) {
		apiBootQuartzService.deleteJob(updQuartzDto.getJobKey());
		create.delete(SYS_QUARTZ).where(SYS_QUARTZ.JOB_KEY.eq(updQuartzDto.getJobKey())).execute();
		return initQuartz(updQuartzDto);
	}

	/**
	 * 暂停定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	@Override
	public Result<String> pauseQuartz(String jobKey) {
		apiBootQuartzService.pauseJob(jobKey);
		create.update(SYS_QUARTZ).set(SYS_QUARTZ.STATUS, 0).where(SYS_QUARTZ.JOB_KEY.eq(jobKey))
				.and(SYS_QUARTZ.STATUS.eq(1)).execute();
		return Result.success();
	}

	/**
	 * 恢复定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	@Override
	public Result<String> resumeQuartz(String jobKey) {
		apiBootQuartzService.resumeJob(jobKey);
		create.update(SYS_QUARTZ).set(SYS_QUARTZ.STATUS, 1).where(SYS_QUARTZ.JOB_KEY.eq(jobKey))
				.and(SYS_QUARTZ.STATUS.eq(0)).execute();
		return Result.success();
	}

	/**
	 * 运行定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	@Override
	public Result<String> runQuartz(String jobKey) {
		try {
			apiBootQuartzService.getScheduler().triggerJob(JobKey.jobKey(jobKey));
		} catch (SchedulerException e) {
			return Result.fail("运行失败" + e.getMessage());
		}
		return Result.success();
	}

	@SuppressWarnings("unchecked")
	private Result<String> initQuartz(QuartzDto dto) {
		Class<? extends QuartzJobBean> name;
		try {
			name = (Class<? extends QuartzJobBean>) Class.forName(dto.getBeanName());
		} catch (ClassNotFoundException e) {
			return Result.fail("bean不存在");
		}
		String jobKey = apiBootQuartzService
				.newJob(ApiBootCronJobWrapper.Context().jobClass(name).cron(dto.getCron()).wrapper());
		SysQuartzRecord record = create.newRecord(SYS_QUARTZ);
		record.from(dto);
		record.setJobKey(jobKey);
		record.setStatus(1);
		record.setCreateTime(LocalDateTime.now());
		record.store();
		return Result.success();
	}
}
