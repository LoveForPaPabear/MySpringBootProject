package com.example.mySpringProject.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 读取redis配置文件
 * @Date 16:39 2019/5/7
 * @Param
 * @return
 **/
@Configuration
public class RedisConfigure {

    @Value("${spring.redis.database}")
    private int dataBase;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;


    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = null;
        if (StringUtils.isNotEmpty(password)) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, dataBase);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null, dataBase);
        }
        return jedisPool;
    }
}
