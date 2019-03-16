package com.sprixin.job.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sprixin.core.model.Result;
import com.sprixin.job.model.ScheduleJobLog;
import com.sprixin.job.service.ScheduleJobLogService;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@RestController
@RequestMapping("/scheduleLog")
@Api(value = "Api-ScheduleJobLogController", description = "定时任务日志接口", tags = "ScheduleJobLogController")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志列表
	 */
	@ApiOperation("定时任务日志列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", value = "当前页数", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "Long", paramType = "query")
	})
	@GetMapping("/list")
	public  Result list(Long current, Long size, ScheduleJobLog scheduleJobLog){
        IPage<ScheduleJobLog> page = scheduleJobLogService.page(new Page<ScheduleJobLog>(current, size), new QueryWrapper<ScheduleJobLog>(scheduleJobLog));
        return  Result.ok(page);
	}

	/**
	 * 定时任务日志信息
	 */
	@ApiOperation(" 定时任务日志信息")
	@ApiImplicitParam(name = "logId", value = "logId", required = true, dataType = "Long", paramType = "query")
	@GetMapping("/info")
	public  Result info(Long logId){
        ScheduleJobLog scheduleJobLog = scheduleJobLogService.getById(logId);
        return  Result.ok(scheduleJobLog);
	}
}
