package com.sprixin.job.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author le.yang
 * @since 2019-01-09
 */
@TableName("sys_schedule_job")
@ApiModel(value="ScheduleJob对象", description="定时任务")
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @ApiModelProperty(value = "任务id")
    @TableId(value = "job_id", type = IdType.AUTO)
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    private String beanName;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "任务状态  0：正常  1：暂停")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
        "jobId=" + jobId +
        ", beanName=" + beanName +
        ", methodName=" + methodName +
        ", params=" + params +
        ", cronExpression=" + cronExpression +
        ", status=" + status +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
