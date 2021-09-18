package com.swagger.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * RedisUtils类(redis工具类)
 *
 * <p>
 * <b>History:</b>
 * <table border="1">
 * <tr>
 * <th>Date</th>
 * <th>Operator</th>
 * <th>Memo</th>
 * </tr>
 * <tr>
 * <td>2021/8/31 10:09</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存,并设置过期时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public boolean set(final String key, String value, long timeout, TimeUnit unit) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
