package com.gjb.shiroTest.oauth;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
import com.gjb.shiroTest.entity.User;
import com.gjb.shiroTest.enums.Privilege;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Spring Security中的 UserDetails 实现
 *
 * @author Shengzhao Li
 */
public class WdcyUserDetails implements UserDetails {

    private static final long serialVersionUID = 3957586021470480642L;


    /**
     * 角色权限 前缀
     *
     * @see org.springframework.security.access.vote.RoleVoter
     */
    protected static final String ROLE_PREFIX = "ROLE_";

    /**
     * 默认的 用户角色
     * ROLE_USER
     */
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name());

    protected User user;

    /**
     * 用户的授权集合
     */
    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();


    public WdcyUserDetails() {
    }

    public WdcyUserDetails(User user) {
        this.user = user;
        initialAuthorities();
    }

    /**
     * 初始化用户角色,权限
     */
    private void initialAuthorities() {
        //Default, everyone have it
        this.grantedAuthorities.add(DEFAULT_USER_ROLE);
        //default user have all privileges
        if (user.defaultUser()) {
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.UNITY.name()));
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.MOBILE.name()));
        } else {
            final List<Privilege> privileges = user.privileges();
            for (Privilege privilege : privileges) {
                this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege.name()));
            }
        }
    }

    /**
     * Return authorities, more information see {@link #initialAuthorities()}
     *
     * @return Collection of GrantedAuthority
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    /* 账户是否未过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*账户是否未锁定 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 密码是否未过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*账户是否启用,默认true (启用)*/
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User user() {
        return user;
    }


    @Override
    public String toString() {
        return "{" +
                "user=" + user +
                ", grantedAuthorities=" + grantedAuthorities +
                '}';
    }
}
