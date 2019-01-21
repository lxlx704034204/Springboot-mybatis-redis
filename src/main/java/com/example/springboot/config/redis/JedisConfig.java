//package com.example.springboot.config.redis;
//
////import com.mljr.auth.config.properties.JedisProperties;
////import com.mljr.auth.util.RedisClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//
//@Configuration
//@EnableConfigurationProperties(JedisProperties.class)
//@ConditionalOnClass(RedisClient.class)  //连通 jedis
//public class JedisConfig extends CachingConfigurerSupport {
//    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);
//
//
//    @Autowired
//    private JedisProperties prop;
//
//    @Bean
//    public JedisPool jedisPool() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(prop.getMaxTotal());
//        config.setMaxIdle(prop.getMaxIdle());
//        config.setMaxWaitMillis(prop.getMaxWaitMillis());
//        return new JedisPool(config, prop.getHost(), prop.getPort(), prop.getTimeOut(), prop.getPassword());
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(RedisClient.class)
//    public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
//        logger.info("初始化……Redis Client==Host={},Port={}", prop.getHost(), prop.getPort());
//        RedisClient redisClient = new RedisClient();
//        redisClient.setJedisPool(pool);
//        return redisClient;
//    }
//
//}