package com.gjb.shiroTest.service.impl;

import com.gjb.shiroTest.dao.OauthClientDetailsMapper;
import com.gjb.shiroTest.entity.Client;
import com.gjb.shiroTest.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public Client loadClientByClientId(String clientId) {

        Client client = oauthClientDetailsMapper.loadClientByClientId(clientId);
        return client;
    }
}
