package com.sprixin.job.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sprixin.job.model.User;
import com.sprixin.job.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * @see com.sprixin.job.service.UserService#add(com.sprixin.job.model.User)
     */
/*    @Override
    public int add(User user) {
        // TODO Auto-generated method stub
        return 0;
    }*/
 
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int add(User user) {
        return jdbcTemplate.update("insert into t_user(user_id, user_name, age, create_time) values(?,?,?, ?)",
              user.getUserId(),user.getUserName(),user.getAge(),user.getCreateTime());
 
    }
}
