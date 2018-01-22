package com.gjb.shiroTest.service.impl;

import com.gjb.shiroTest.dao.PrivilegeMapper;
import com.gjb.shiroTest.dao.UserMapper;
import com.gjb.shiroTest.dto.UserJsonDto;
import com.gjb.shiroTest.entity.PrivilegeEntity;
import com.gjb.shiroTest.entity.User;
import com.gjb.shiroTest.enums.Privilege;
import com.gjb.shiroTest.oauth.WdcyUserDetails;
import com.gjb.shiroTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userMapper.findByUsername(username);
        if (user == null || user.archived()) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }
        List<PrivilegeEntity> privilegeEntityList = privilegeMapper.findPrivileges(user.getId());
        if(privilegeEntityList != null && privilegeEntityList.size() > 0){
            List<Privilege> privileges = new ArrayList<>(privilegeEntityList.size());
            for(PrivilegeEntity privilegeEntity : privilegeEntityList){
                privileges.add(Privilege.valueOf(privilegeEntity.getPrivilege()));
            }
            user.setPrivileges(privileges);
        }


        //privileges.addAll(strings.stream().map(Privilege::valueOf).collect(Collectors.toList()));

        return new WdcyUserDetails(user);
    }

    @Override
    public UserJsonDto loadCurrentUserJsonDto() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = authentication.getPrincipal();

        if (authentication instanceof OAuth2Authentication &&
                (principal instanceof String || principal instanceof User)) {
            return loadOauthUserJsonDto((OAuth2Authentication) authentication);
        }else {
            final WdcyUserDetails userDetails = (WdcyUserDetails) principal;
            return new UserJsonDto(userDetails.user());
        }
    }

    private UserJsonDto loadOauthUserJsonDto(OAuth2Authentication oAuth2Authentication) {
        UserJsonDto userJsonDto = new UserJsonDto();
        userJsonDto.setUsername(oAuth2Authentication.getName());

        Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            userJsonDto.getPrivileges().add(authority.getAuthority());
        }

        return userJsonDto;
    }
}
