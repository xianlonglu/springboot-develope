package com.lxl.datasource.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lxl.datasource.DynamicDataSource;
import com.lxl.datasource.annotation.DataSourceIp;

/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
@Order(-100)
public class DataSourceAspect {
	/**
	 * 切入点
	 */
	@Pointcut("@annotation(com.lxl.datasource.annotation.DataSource)")
	public void dataSourcePointCut() {

	}

	/**
	 * 环绕通知
	 * 
	 * @param point
	 *            连接点
	 * @return
	 * @throws Throwable
	 */
	@Around("dataSourcePointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		// 获取@DataSourceIp注解的方法参数下标
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();

		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		int index = 0; // 数据源ip参数位置
		boolean isFind = false;
		ok: for (Annotation[] annotations : parameterAnnotations) {
			index++;
			for (Annotation annotation : annotations) {
				if (annotation instanceof DataSourceIp) {
					isFind = true;
					break ok; // 跳出双层循环
				}
			}
		}
		if (isFind) {
			String dataSourceIp = "";
			Object[] args = point.getArgs();
			if (ArrayUtils.isNotEmpty(args)) {
				dataSourceIp = (String) args[index - 1];
			}
			DynamicDataSource.setDataSource(dataSourceIp);
		}

		try {
			return point.proceed();
		} finally {
			DynamicDataSource.clearDataSource();
		}
	}
}
