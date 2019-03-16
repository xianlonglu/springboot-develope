package com.sprixin.job.util;

import com.sprixin.job.model.ScheduleJob;
import com.sprixin.job.model.ScheduleJobLog;
import com.sprixin.job.service.ScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CustomJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Object obj = context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJob scheduleJob = new ScheduleJob();
        BeanUtils.copyProperties(obj, scheduleJob);

        Long jobId = scheduleJob.getJobId();
        String beanName = scheduleJob.getBeanName();
        String methodName = scheduleJob.getMethodName();
        String params = scheduleJob.getParams();

        //数据库保存执行记录
        ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(jobId);
        log.setBeanName(beanName);
        log.setMethodName(methodName);
        log.setParams(params);
        log.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + jobId);
            Object bean = applicationContext.getBean(beanName);

            Method method;
            if (StringUtils.isNotBlank(params)) {
                method = bean.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = bean.getClass().getDeclaredMethod(methodName);
            }
            // 反射调用方法
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(bean, params);
            } else {
                method.invoke(bean);
            }

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);
            //任务状态    0：成功    1：失败
            log.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + jobId + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + jobId, e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);

            //任务状态    0：成功    1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(log);
        }
    }

}