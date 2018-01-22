package com.gjb.shiroTest.dao;

import com.gjb.shiroTest.entity.Client;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-12
 */
public interface OauthClientDetailsMapper {

    Client loadClientByClientId(String clientId);
}
