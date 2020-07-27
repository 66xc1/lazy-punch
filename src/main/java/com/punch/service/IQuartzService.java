package com.punch.service;

import java.util.List;
import java.util.Map;

import com.punch.common.entity.Result;
import com.punch.model.QuartzDto;
import com.punch.model.UpdQuartzDto;

/**
 * @author xiachao
 * @date 2020/07/22 14:37
 */
public interface IQuartzService {

	/**
	 * 查询定时任务
	 *
	 * @return result
	 */
	Result<List<Map<String, Object>>> getQuartzList();

	/**
	 * 新增定时任务
	 *
	 * @param quartzDto dto
	 * @return result
	 */
	Result<String> addQuartz(QuartzDto quartzDto);

	/**
	 * 删除定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	Result<String> delQuartz(String jobKey);

	/**
	 * 修改定时任务
	 *
	 * @param updQuartzDto updQuartz
	 * @return result
	 */
	Result<String> updQuartz(UpdQuartzDto updQuartzDto);

	/**
	 * 暂停定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	Result<String> pauseQuartz(String jobKey);

	/**
	 * 恢复定时任务
	 *
	 * @param jobKey jobKey
	 * @return result
	 */
	Result<String> resumeQuartz(String jobKey);
}
