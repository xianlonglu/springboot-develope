package com.lxl.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.model.Role;


/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author xianlong.lu
 * @since 2019-06s-01
 */
public interface RoleService extends IService<Role>{

	
	List<Role> getList(String dataSourceIp);


}
