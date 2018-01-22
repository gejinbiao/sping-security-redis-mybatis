package com.gjb.shiroTest.dao;

import com.gjb.shiroTest.entity.PrivilegeEntity;

import java.util.List;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-18
 */
public interface PrivilegeMapper {


    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    List<PrivilegeEntity> findPrivileges(Integer userId);
}
