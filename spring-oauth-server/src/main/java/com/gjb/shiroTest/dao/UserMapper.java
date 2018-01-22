package com.gjb.shiroTest.dao;

import com.gjb.shiroTest.entity.User;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
public interface UserMapper {

    User findByUsername(String userName);
}
