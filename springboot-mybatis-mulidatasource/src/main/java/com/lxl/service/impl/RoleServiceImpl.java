package com.lxl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.dao.RoleMapper;
import com.lxl.datasource.annotation.DataSource;
import com.lxl.datasource.annotation.DataSourceIp;
import com.lxl.model.Role;
import com.lxl.service.RoleService;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author xianlong.lu
 * @since 2019-06-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Override
	@DataSource
	public List<Role> getList(@DataSourceIp String dataSourceIp) {
		return baseMapper.selectList(null);
	}

}
