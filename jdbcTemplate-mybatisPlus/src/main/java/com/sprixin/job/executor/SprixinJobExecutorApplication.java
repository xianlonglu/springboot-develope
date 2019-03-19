/**
 * Project Name:sprixin-job-executor
 * File Name:SprixinJobExecutorApplication.java
 * Package com.sprixin.job.executor:
 * Date:2019年2月20日下午7:29:47
 * Copyright (c) 2019, SPRIXIN.com All Rights Reserved.
 */
package com.sprixin.job.executor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**执行器启动类<br/><br/>
 * 创建时间： 2019年2月20日 下午7:29:47 <br/>
 * @author yao
 */
@SpringBootApplication
@MapperScan("com.sprixin.job.dao")
public class SprixinJobExecutorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SprixinJobExecutorApplication.class, args);
    }
}
