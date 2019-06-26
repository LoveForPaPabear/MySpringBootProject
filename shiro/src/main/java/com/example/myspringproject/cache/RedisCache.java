package com.example.myspringproject.cache;

import com.example.myspringproject.utils.JedisUtil;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description
 * @Date 16:19 2019/6/26
 * @Param
 * @return
 **/
public class RedisCache<K, V> implements Cache<K, V> {

    private String keyPrefix = "shiro_redis_session:";

    @Override
    public Object get(Object key) throws CacheException {

        byte[] bytes = getByteKey(key);
        byte[] value = JedisUtil.getJedis().get(bytes);
        if (value == null) {
            return null;
        }
        return SerializationUtils.deserialize(value);
    }

    private byte[] getByteKey(Object k) {
        if (k instanceof String) {
            String key = keyPrefix + k;
            return key.getBytes();
        } else {
            return SerializationUtils.serialize((Serializable) k);
        }
    }

    /**
     * 将shiro的缓存保存到redis中
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {

        Jedis jedis = JedisUtil.getJedis();

        jedis.set(getByteKey(key), SerializationUtils.serialize((Serializable) value));
        byte[] bytes = jedis.get(getByteKey(key));
        Object object = SerializationUtils.deserialize(bytes);

        return object;

    }

    @Override
    public Object remove(Object key) throws CacheException {
        Jedis jedis = JedisUtil.getJedis();

        byte[] bytes = jedis.get(getByteKey(key));

        jedis.del(getByteKey(key));

        return SerializationUtils.deserialize(bytes);

    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        JedisUtil.getJedis().flushDB();
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        Long size = JedisUtil.getJedis().dbSize();
        return size.intValue();
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
        Set<byte[]> keys = JedisUtil.getJedis().keys(new String("*").getBytes());
        Set<Object> set = new HashSet<>();
        for (byte[] bs : keys) {
            set.add(SerializationUtils.deserialize(bs));
        }
        return set;
    }


    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
        Set keys = this.keys();

        List<Object> values = new ArrayList<Object>();
        for (Object key : keys) {
            byte[] bytes = JedisUtil.getJedis().get(getByteKey(key));
            values.add(SerializationUtils.deserialize(bytes));
        }
        return values;
    }
}