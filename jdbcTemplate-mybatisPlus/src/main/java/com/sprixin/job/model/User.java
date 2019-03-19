package com.sprixin.job.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_user")//指定表名
public class User {
    private Integer userId;

    private String userName;

    private Integer age;

    private Long createTime;

    public User(Integer userId, String userName, Integer age, Long createTime) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.createTime = createTime;
    }
    

    @Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", age=" + age + ", createTime=" + createTime
				+ "]";
	}


	public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}