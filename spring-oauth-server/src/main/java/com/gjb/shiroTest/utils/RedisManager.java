package com.gjb.shiroTest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-17
 */
public class RedisManager {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean isExist(String key) {
        return redisTemplate.hasKey(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Boolean setIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void multiSet(Map<? extends String, Object> m) {
        redisTemplate.opsForValue().multiSet(m);
    }

    public Boolean multiSetIfAbsent(Map<? extends String, Object> m) {
        return redisTemplate.opsForValue().multiSetIfAbsent(m);
    }

/*    public <T> T get(Object key, Class<T> classOfT) {
        return Primitives.wrap(classOfT).cast(
                redisTemplate.opsForValue().get(key));
    }

    public <T> T getAndSet(String key, Object value, Class<T> classOfT) {
        return Primitives.wrap(classOfT).cast(
                redisTemplate.opsForValue().getAndSet(key, value));
    }*/

    public List<Object> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    public String get(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    public void set(String key, Object value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteAll(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * ============域访问器==============
     */

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
