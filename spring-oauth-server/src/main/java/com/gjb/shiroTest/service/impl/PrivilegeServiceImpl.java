package com.gjb.shiroTest.service.impl;

import com.gjb.shiroTest.dao.PrivilegeMapper;
import com.gjb.shiroTest.entity.PrivilegeEntity;
import com.gjb.shiroTest.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-18
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PrivilegeServiceImpl implements PrivilegeService {


    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public List<PrivilegeEntity> findPrivileges(Integer userId) {
        return privilegeMapper.findPrivileges(userId);
    }
}
