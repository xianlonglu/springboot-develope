/**
 * Project Name:sprixin-job-executor
 * File Name:SprixinJobConfig.java
 * Package com.sprixin.job.executor.core.config:
 * Date:2019年2月20日下午7:29:47
 * Copyright (c) 2019, SPRIXIN.com All Rights Reserved.
 */
package com.sprixin.job.executor.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;

/**
 * 执行器配置类<br/><br/>
 * 创建时间： 2019年2月20日 下午7:50:15 <br/>
 * @author yao
 */
@Configuration
@ComponentScan(basePackages = "com.sprixin")
public class SprixinJobConfig {
    private Logger logger = LoggerFactory.getLogger(SprixinJobConfig.class);

    @Value("${sprixin.job.admin.addresses}")
    private String adminAddresses;

    @Value("${sprixin.job.executor.appname}")
    private String appName;

    @Value("${sprixin.job.executor.ip}")
    private String ip;

    @Value("${sprixin.job.executor.port}")
    private int port;

    @Value("${sprixin.job.accessToken}")
    private String accessToken;

    @Value("${sprixin.job.executor.logpath}")
    private String logPath;

    @Value("${sprixin.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> sprixin-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

}