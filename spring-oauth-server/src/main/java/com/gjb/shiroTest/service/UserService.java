package com.gjb.shiroTest.service;

import com.gjb.shiroTest.dto.UserJsonDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
public interface UserService  extends UserDetailsService {

    /**
     * 查询用户信息
     * @return
     */
    public UserJsonDto loadCurrentUserJsonDto();

    /*UserJsonDto loadCurrentUserJsonDto();

    UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);

    boolean isExistedUsername(String username);

    String saveUser(UserFormDto formDto);*/
}
