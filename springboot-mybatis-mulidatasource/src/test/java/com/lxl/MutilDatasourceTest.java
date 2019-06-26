package com.lxl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lxl.model.Role;
import com.lxl.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutilApplication.class)
public class MutilDatasourceTest {

	@Autowired
	private RoleService roleService;

	/**
	 */
	@Test
	public void get() {
		List<Role> roleList1 = roleService.getList("127.0.0.1");
		System.err.println(roleList1.size() + "=" + roleList1);

		List<Role> roleList2 = roleService.getList("10.10.12.198");
		System.err.println(roleList2.size() + "=" + roleList2);

	}

}
