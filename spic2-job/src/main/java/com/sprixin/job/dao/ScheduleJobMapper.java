package com.sprixin.job.dao;

import com.sprixin.job.model.ScheduleJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 定时任务 Mapper 接口
 * </p>
 *
 * @author le.yang
 * @since 2019-01-08
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    /**
     * 批量更新状态
     */
    int updateStatusBatch(Map<String, Object> map);
}
