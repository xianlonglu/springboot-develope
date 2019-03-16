package com.sprixin.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sprixin.job.model.ScheduleJob;

import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author le.yang
 * @since 2019-01-08
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

	void pause(List<Long> jobIds);

	void resume(List<Long> jobIds);

	void run(List<Long> jobIds);

	void deleteBatch(List<Long> jobIds);

	void saveScheduleJob(ScheduleJob scheduleJob);

	int updateStatusBatch(List<Long> jobIds, int status);

	void updateScheduleJob(ScheduleJob scheduleJob);
}
