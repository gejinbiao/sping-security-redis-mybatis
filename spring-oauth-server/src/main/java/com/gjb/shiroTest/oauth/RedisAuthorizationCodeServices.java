package com.gjb.shiroTest.oauth;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

import java.util.concurrent.TimeUnit;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-17
 */
public class RedisAuthorizationCodeServices implements AuthorizationCodeServices {

    private RandomValueStringGenerator generator = new RandomValueStringGenerator(10);

    private static final String CODE_PATH = "code:";

    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String code = generator.generate();
        redisTemplate.opsForValue().set(CODE_PATH + code,authentication,60 * 10, TimeUnit.SECONDS);
        return code;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
        OAuth2Authentication auth = (OAuth2Authentication) redisTemplate.opsForValue().get(CODE_PATH + code);
        redisTemplate.delete(CODE_PATH + code);
        return auth;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
