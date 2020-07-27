package com.punch.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.minbox.framework.api.boot.plugin.quartz.ApiBootQuartzService;
import org.minbox.framework.api.boot.plugin.quartz.wrapper.ApiBootJobParamWrapper;
import org.minbox.framework.api.boot.plugin.quartz.wrapper.support.ApiBootOnceJobWrapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.punch.common.entity.Result;
import com.punch.model.JobKeyDto;
import com.punch.model.QuartzDto;
import com.punch.model.UpdQuartzDto;
import com.punch.service.IQuartzService;
import com.punch.task.PunchTask;

/**
 * @author xiachao
 * @date 2020/07/22 12:05
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

	@Resource
	private IQuartzService quartzService;

	@PostMapping(value = "/getQuartzList")
	public Result<List<Map<String, Object>>> getQuartzList() {
		return quartzService.getQuartzList();
	}

	@PostMapping(value = "/addQuartz")
	public Result<String> addQuartz(@RequestBody @Validated QuartzDto quartzDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return quartzService.addQuartz(quartzDto);
	}

	@PostMapping(value = "/delQuartz")
	public Result<String> delQuartz(@RequestBody @Validated JobKeyDto jobKeyDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return quartzService.delQuartz(jobKeyDto.getJobKey());
	}

	@PostMapping(value = "/updQuartz")
	public Result<String> updQuartz(@RequestBody @Validated UpdQuartzDto updQuartzDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return quartzService.updQuartz(updQuartzDto);
	}

	@PostMapping(value = "/pauseQuartz")
	public Result<String> pauseQuartz(@RequestBody @Validated JobKeyDto jobKeyDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return quartzService.pauseQuartz(jobKeyDto.getJobKey());
	}

	@PostMapping(value = "/resumeQuartz")
	public Result<String> resumeQuartz(@RequestBody @Validated JobKeyDto jobKeyDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return quartzService.resumeQuartz(jobKeyDto.getJobKey());
	}

	@Resource
	private ApiBootQuartzService apiBootQuartzService;

	@PostMapping(value = "/test")
	public Result<String> test() {
		Map<String, Object> map = new HashMap<>(10);
		map.put("test", "1");
		map.put("solo", 222222222);

		String jobKey = apiBootQuartzService.newJob(ApiBootOnceJobWrapper.Context().jobClass(PunchTask.class)
				.param(ApiBootJobParamWrapper.wrapper().put("map", JSON.toJSONString(map)))
				.startAtTime(new Date(System.currentTimeMillis() + 10000)).wrapper());
		return Result.success(jobKey);
	}

	@PostMapping(value = "/del")
	public Result<String> del(@RequestParam String jobKey) {
		apiBootQuartzService.deleteJob(jobKey);
		return Result.success();
	}
}
