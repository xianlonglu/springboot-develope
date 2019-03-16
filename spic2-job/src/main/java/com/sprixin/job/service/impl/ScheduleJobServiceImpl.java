package com.sprixin.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sprixin.job.dao.ScheduleJobMapper;
import com.sprixin.job.model.ScheduleJob;
import com.sprixin.job.service.ScheduleJobService;
import com.sprixin.job.util.CustomJob;
import com.sprixin.job.util.ScheduleStatusEnum;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author le.yang
 * @since 2019-01-08
 */
@Service
@Transactional
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
    @Autowired
    private Scheduler scheduler;
	
    @Override
    public void saveScheduleJob(ScheduleJob scheduleJob) {
        try {
            save(scheduleJob);
            // 构建Job详情
            JobDetail jobDetail = JobBuilder.newJob().newJob(CustomJob.class).withIdentity(JobKey.jobKey(scheduleJob.getJobId().toString())).build();
            // 构建cron表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            // 构建器构建触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey(scheduleJob.getJobId().toString())).withSchedule(cronScheduleBuilder).build();
            // 将scheduleJob放入JobDataMap中
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
            // 添加任务
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            throw new RuntimeException("创建定时任务失败！", e);
        }
    }

    @Override
    public int updateStatusBatch(List<Long> jobIds, int status){
        Map<String, Object> map = new HashMap<>();
        map.put("list", jobIds);
        map.put("status", status);
        return baseMapper.updateStatusBatch(map);
    }


    @Override
    public void updateScheduleJob(ScheduleJob scheduleJob) {
        try {
            updateById(scheduleJob);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey(scheduleJob.getJobId().toString())).withSchedule(cronScheduleBuilder).build();
            cronTrigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
            scheduler.rescheduleJob(TriggerKey.triggerKey(scheduleJob.getJobId().toString()), cronTrigger);
        } catch (Exception e) {
            throw new RuntimeException("更新定时任务失败！",e);
        }
    }

    @Override
    public void deleteBatch(List<Long> jobIds) {
        try {
            removeByIds(jobIds);
            List<JobKey> jobKeys = new ArrayList<>();
            for (Long jobId : jobIds) {
                jobKeys.add(JobKey.jobKey(jobId.toString()));
            }
            scheduler.deleteJobs(jobKeys);
        } catch (Exception e) {
            throw new RuntimeException("删除定时任务失败！",e);
        }
    }

    @Override
    public void pause(List<Long> jobIds) {
        try {
            updateStatusBatch(jobIds, ScheduleStatusEnum.PAUSE.getValue());
            for (Long jobId : jobIds) {
                scheduler.pauseJob(JobKey.jobKey(jobId.toString()));
            }
        } catch (Exception e) {
            throw new RuntimeException("暂停定时任务失败！",e);
        }
    }

    @Override
    public void resume(List<Long> jobIds) {
        try {
            updateStatusBatch(jobIds, ScheduleStatusEnum.NORMAL.getValue());
            for (Long jobId : jobIds) {
                scheduler.resumeJob(JobKey.jobKey(jobId.toString()));
            }
        } catch (Exception e) {
            throw new RuntimeException("恢复定时任务失败！",e);
        }
    }

    @Override
    public void run(List<Long> jobIds) {
        try {
            for (Long jobId : jobIds) {
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put(ScheduleJob.JOB_PARAM_KEY.toString(), getById(jobId));
                scheduler.triggerJob(JobKey.jobKey(jobId.toString()), jobDataMap);
            }
        } catch (Exception e) {
            throw new RuntimeException("运行定时任务失败！",e);
        }
    }

}
