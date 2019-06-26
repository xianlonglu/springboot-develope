package com.lxl.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	// 用来保存数据源与获取数据源
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	/**
	 * creates a new instance of DynamicDataSource
	 * 
	 * @param defaultTargetDataSource
	 *            默认数据源
	 * @param targetDataSources
	 *            其他数据源
	 */
	public DynamicDataSource(DataSource defaultTargetDataSource,
			Map<Object, Object> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(targetDataSources);
		super.afterPropertiesSet();
	}

	/**
	 * 设置数据源
	 * 
	 * @param dataSource
	 */
	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	/**
	 * 获取数据源
	 * 
	 * @param dataSource
	 */
	public static String getDataSource() {
		return contextHolder.get();
	}

	/**
	 * 清空数据源
	 * 
	 * @param dataSource
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getDataSource();
	}

}
