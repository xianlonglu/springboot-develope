package com.sprixin.job.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sprixin.core.model.Result;
import com.sprixin.job.model.ScheduleJob;
import com.sprixin.job.service.ScheduleJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

/**
 * 定时任务
 */
@RestController
@RequestMapping("/schedule")
@Api(value = "Api-ScheduleJobController", description = "定时任务接口", tags = "ScheduleJobController")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务列表
	 */
	@ApiOperation("定时任务列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", value = "当前页数", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "Long", paramType = "query") })
	@GetMapping("/list")
	public Result list(Long current, Long size, ScheduleJob scheduleJob) {
		IPage<ScheduleJob> page = scheduleJobService.page(new Page<>(current,
				size), new QueryWrapper<ScheduleJob>(scheduleJob));
		return Result.ok(page);
	}

	/**
	 * 定时任务信息
	 */
	@ApiOperation(" 定时任务信息")
	@ApiImplicitParam(name = "jobId", value = "jobId", required = true, dataType = "Long", paramType = "query")
	@GetMapping("/info")
	public Result info(Long jobId) {
		ScheduleJob scheduleJob = scheduleJobService.getById(jobId);
		return Result.ok(scheduleJob);
	}

	/**
	 * 保存定时任务
	 */
	@ApiOperation("保存定时任务")
	@PostMapping("/save")
	public Result save(ScheduleJob scheduleJob) {
		scheduleJobService.saveScheduleJob(scheduleJob);
		return Result.ok();
	}

	/**
	 * 修改定时任务
	 */
	@ApiOperation("修改定时任务")
	@PostMapping("/update")
	public Result update(ScheduleJob scheduleJob) {
		scheduleJobService.updateScheduleJob(scheduleJob);
		return Result.ok();
	}

	/**
	 * 删除定时任务
	 */
	@ApiOperation("删除定时任务")
	@ApiImplicitParam(name = "jobIds", value = "jobIds", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public Result delete(@RequestBody List<Long> jobIds) {
		scheduleJobService.deleteBatch(jobIds);
		return Result.ok();
	}

	/**
	 * 立即执行任务
	 */
	@ApiOperation("立即执行任务")
	@ApiImplicitParam(name = "jobIds", value = "jobIds", required = true, dataType = "Long[]")
	@PostMapping("/run")
	public Result run(@RequestBody List<Long> jobIds) {
		scheduleJobService.run(jobIds);
		return Result.ok();
	}

	/**
	 * 暂停定时任务
	 */
	@ApiOperation("暂停定时任务")
	@ApiImplicitParam(name = "jobIds", value = "jobIds", required = true, dataType = "Long[]")
	@PostMapping("/pause")
	public Result pause(@RequestBody List<Long> jobIds) {
		scheduleJobService.pause(jobIds);
		return Result.ok();
	}

	/**
	 * 恢复定时任务
	 */
	@ApiOperation("恢复定时任务")
	@ApiImplicitParam(name = "jobIds", value = "jobIds", required = true, dataType = "Long[]")
	@PostMapping("/resume")
	public Result resume(@RequestBody List<Long> jobIds) {
		scheduleJobService.resume(jobIds);
		return Result.ok();
	}

}
