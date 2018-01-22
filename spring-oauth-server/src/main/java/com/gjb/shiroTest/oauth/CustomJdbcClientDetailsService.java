package com.gjb.shiroTest.oauth;

import com.gjb.shiroTest.entity.Client;
import com.gjb.shiroTest.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CustomJdbcClientDetailsService implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    /**
     * 查询客户端
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId)  {


        Client client = oauthClientDetailsService.loadClientByClientId(clientId);
            if(client == null){
                throw new NoSuchClientException("No client with requested id: " + clientId);
            }
        BaseClientDetails baseClientDetails = new BaseClientDetails(client.getClientId(),client.getResourceIds(),client.getScope()
        ,client.getAuthorizedGrantTypes(),client.getAuthorities());
        baseClientDetails.setClientSecret(client.getClientSecret());
        if(!StringUtils.isEmpty(client.getAutoApproveScopes())){
            if (StringUtils.hasText(client.getAutoApproveScopes())) {
                Set<String> autoApproveScopes = StringUtils.commaDelimitedListToSet(client.getClientSecret());
                if (!autoApproveScopes.isEmpty()) {
                    baseClientDetails.setAutoApproveScopes(autoApproveScopes);
                }
            }
        }



        return baseClientDetails;
    }
}
