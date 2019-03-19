/**
 * Project Name:sprixin-job-executor
 * File Name:DemoJobHandler.java
 * Package com.sprixin.job.executor.service.jobhandler
 * Date:2019年2月20日下午7:29:47
 * Copyright (c) 2019, SPRIXIN.com All Rights Reserved.
 */

package com.sprixin.job.executor.service.jobhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sprixin.job.dao.UserMapper;
import com.sprixin.job.model.User;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.sprixin.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；

 * 创建时间： 2019年2月20日 下午7:51:05 <br/>
 * @author yao
 */
@JobHandler(value="dbJobHandler2")
@Component
public class DbJobHandler2 extends IJobHandler {

    //基于mybatis-plus
    @Autowired
    private UserMapper userMapper;
    
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("SPRIXIN-JOB, Hello World.");

		User user = new User();
		user.setUserName("jim");
		userMapper.insert(user);

		/*for (int i = 0; i < 5; i++) {
			XxlJobLogger.log("beat at:" + i);
			TimeUnit.SECONDS.sleep(2);
		}*/
		return SUCCESS;
	}

}
