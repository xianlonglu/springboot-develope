package com.sprixin.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sprixin.job.dao.ScheduleJobLogMapper;
import com.sprixin.job.model.ScheduleJobLog;
import com.sprixin.job.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author le.yang
 * @since 2019-01-08
 */
@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

}
