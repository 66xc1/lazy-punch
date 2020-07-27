package com.punch.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punch.common.entity.Result;
import com.punch.model.JobKeyDto;
import com.punch.model.QuartzDto;
import com.punch.model.UpdQuartzDto;
import com.punch.service.IQuartzService;

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

	@PostMapping(value = "/runQuartz")
	public Result<String> runQuartz(@RequestBody @Validated JobKeyDto jobKeyDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return quartzService.runQuartz(jobKeyDto.getJobKey());
	}

}
