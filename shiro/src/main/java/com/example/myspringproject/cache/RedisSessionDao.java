package com.example.myspringproject.cache;

import com.example.myspringproject.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

@Slf4j
public class RedisSessionDao extends CachingSessionDAO {

    private static final String PREFIX = "SHIRO_SESSION_ID";

    private static final int EXPRIE = 10000;


    @Override
    protected Serializable doCreate(Session session) {
        log.info("--------doCreate-----");
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        Jedis jedis = JedisUtil.getJedis();
        session.setTimeout(EXPRIE * 1000);
        /*jedis.set(getByteKey(serializable),SerializationUtils.serialize((Serializable)session));
        jedis.expire(SerializationUtils.serialize(getByteKey(serializable)),EXPRIE);*/
        jedis.setex(getByteKey(serializable), EXPRIE, SerializationUtils.serialize((Serializable) session));
        JedisUtil.closeJedis(jedis);
        return serializable;
    }


    @Override
    protected Session doReadSession(Serializable serializable) {
        log.info("--------doReadSession-----");
        Jedis jedis = JedisUtil.getJedis();
        Session session = null;
        byte[] s = jedis.get(getByteKey(serializable));
        if (s != null) {
            session = SerializationUtils.deserialize(s);
            jedis.expire((PREFIX + serializable).getBytes(), EXPRIE);
        }
        //判断是否有会话  没有返回NULL
        if (session == null) {
            return null;
        }
        JedisUtil.closeJedis(jedis);
        return session;
    }

    private byte[] getByteKey(Object k) {
        if (k instanceof String) {
            String key = PREFIX + k;
            return key.getBytes();
        } else {
            return SerializationUtils.serialize((Serializable) k);
        }
    }

    @Override
    protected void doUpdate(Session session) {
        log.info("--------doUpdate-----");
        if (session == null) {
            return;
        }
        Jedis jedis = JedisUtil.getJedis();
        session.setTimeout(EXPRIE * 1000);
       /*jedis.set(getByteKey(session.getId()),SerializationUtils.serialize((Serializable)session));
       jedis.expire(SerializationUtils.serialize((PREFIX+session.getId())),EXPRIE);*/
        jedis.setex(getByteKey(session.getId()), EXPRIE, SerializationUtils.serialize((Serializable) session));
    }


    @Override
    protected void doDelete(Session session) {
        log.info("--------doDelete-----");
        Jedis jedis = JedisUtil.getJedis();
        jedis.del(getByteKey(session.getId()));
        JedisUtil.closeJedis(jedis);
    }


}