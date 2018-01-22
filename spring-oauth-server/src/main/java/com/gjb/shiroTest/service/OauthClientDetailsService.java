package com.gjb.shiroTest.service;

import com.gjb.shiroTest.entity.Client;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-12
 */
public interface OauthClientDetailsService {


    /**
     * 根据clientid查询信息
     * @param clientId
     * @return
     */
    Client loadClientByClientId(String clientId);
}
