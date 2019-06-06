package com.example.myspringproject.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RedisCache<K,V> implements Cache<K,V> {

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    private String keyPrefix = "shiro_redis_session:";


    @Override
    public Object get(Object key) throws CacheException {

//        byte[] bytes = getByteKey(key);
//        byte[] value = JedisClientSingle.getJedis().get(bytes);
//        if(value == null){
//            return null;
//        }
//        return ByteSourceUtils.deserialize(value);
        return null;
    }

    /**
     * 将shiro的缓存保存到redis中
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {

//        Jedis jedis = JedisClientSingle.getJedis();
//
//        jedis.set(getByteKey(key), ByteSourceUtils.serialize((Serializable)value));
//        byte[] bytes = jedis.get(getByteKey(key));
//        Object object = ByteSourceUtils.deserialize(bytes);

        return new Object();

    }

    @Override
    public Object remove(Object key) throws CacheException {
//        Jedis jedis = JedisClientSingle.getJedis();
//
//        byte[] bytes = jedis.get(getByteKey(key));
//
//        jedis.del(getByteKey(key));
//
//        return ByteSourceUtils.deserialize(bytes);

        return new Object();
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
//        JedisClientSingle.getJedis().flushDB();
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
//        Long size = JedisClientSingle.getJedis().dbSize();
//        return size.intValue();
        return 1;
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
//        Set<byte[]> keys = JedisClientSingle.getJedis().keys(new String("*").getBytes());
//        Set<Object> set = new HashSet<Object>();
//        for (byte[] bs : keys) {
//            set.add(ByteSourceUtils.deserialize(bs));
//        }
        return null;
    }


    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
        Set keys = this.keys();

        List<Object> values = new ArrayList<Object>();
//        for (Object key : keys) {
//            byte[] bytes = JedisClientSingle.getJedis().get(getByteKey(key));
//            values.add(ByteSourceUtils.deserialize(bytes));
//        }
        return values;
    }
}