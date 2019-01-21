//package com.example.springboot.config.redis;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.Arrays;
// //https://blog.csdn.net/u011851478/article/details/70239722
//@EnableCaching
//@Configuration
//public class RedisCacheConfig22 extends CachingConfigurerSupport {
//
//    @Autowired
//    private JedisConnectionFactory jedisConnectionFactory;
//
//    @Bean
//    public RedisTemplate redisTemplate() {
//        StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory);
//
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        String[] cacheNames = {"app_default", "users", "blogs", "goods", "configs", "info"};
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate(), Arrays.asList(cacheNames));
//        redisCacheManager.setDefaultExpiration(86400);
//        return redisCacheManager;
//    }
//
////    @Bean
////    public Cache cache() {
////        return cacheManager().getCache("app_default");
////    }
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append("::" + method.getName() + ":");
//            for (Object obj : objects) {
//                sb.append(obj.toString());
//            }
//            return sb.toString();
//        };
//    }
//}