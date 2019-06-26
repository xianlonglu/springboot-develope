package com.lxl.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {

	@Value("${spring.datasource.druid.first.ip}")
	private String firstIp;
	@Value("${spring.datasource.druid.second.ip}")
	private String secondIp;
	@Value("${spring.datasource.druid.third.ip}")
	private String thirdIp;

	/**
	 * 第一数据源
	 * 
	 * @return
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.druid.first")
	public DataSource firstDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.druid.second")
	public DataSource secondDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.druid.third")
	public DataSource thirdDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * depends-on的作用一般是一个bean依赖于另外一个bean，被依赖的bean一般用于一些初始化和收尾的工作：先执行被依赖bean的init方法，然后执行依赖bean的方法
	 * @param firstDataSource
	 * @param secondDataSource
	 * @param thirdDataSource
	 * @return
	 */
	@Bean
	@Primary
	@DependsOn({ "firstDataSource", "secondDataSource", "thirdDataSource" })
	public DynamicDataSource dataSource1(DataSource firstDataSource, DataSource secondDataSource,
			DataSource thirdDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(firstIp, firstDataSource);
		targetDataSources.put(secondIp, secondDataSource);
		targetDataSources.put(thirdIp, thirdDataSource);
		return new DynamicDataSource(firstDataSource, targetDataSources);
	}

}
