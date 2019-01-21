//package com.example.springboot.config.redis;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.cache.interceptor.KeyGenerator;
//
///**
// *
// * Type Name：RedisCacheConfig
// * Create Date：2015-09-21
// *
// *
// */
//@Configuration
//@EnableCaching
//public class RedisCacheConfig21 extends CachingConfigurerSupport {
//
//    @Value("${spring.redis.host}") String host;
//    @Value("${spring.redis.port}") int port;
//
//    @Override
//	@Bean
//	public KeyGenerator keyGenerator() {
//		return (o, method, objects) -> {
//			StringBuilder sb = new StringBuilder();
//			sb.append(method.getName() + "(");
//			for (Object obj : objects) {
//				sb.append(obj.toString());
//			}
//			sb.append(")");
//			return sb.toString();
//		};
//	}
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//
//        // Defaults
//        redisConnectionFactory.setHostName(host);
//        redisConnectionFactory.setPort(port);
//        return redisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//
//        // Number of seconds before expiration. Defaults to unlimited (0)
//        cacheManager.setDefaultExpiration(3000); // Sets the default expire time (in seconds)
//        return cacheManager;
//    }
//
//
//}
