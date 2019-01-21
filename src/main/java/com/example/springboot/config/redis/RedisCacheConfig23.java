//package com.example.springboot.config.redis;
//
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
//
//import java.util.ResourceBundle;
//
///** https://www.jianshu.com/p/268e69f64b66
// * <p>
// * spring cache和redis的结合配置类
// * </p>
// *
// * @author wangguangdong
// * @version 1.0
// * @Date 16/2/25
// * @see RedisCacheManager
// * @see JedisConnectionFactory
// * @see RedisTemplate
// * @see KeyGenerator
// */
//@Configuration
//@EnableCaching
//public class RedisCacheConfig23 extends CachingConfigurerSupport {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//
//    private String redisHost = host;
//    private int redisPort = port;
//    private String redisPassword = password;
//    private int expireTime = timeout;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//        // Defaults
//        redisConnectionFactory.setHostName(redisHost);
//        redisConnectionFactory.setPort(redisPort);
//        redisConnectionFactory.setPassword(redisPassword);
//        return redisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    /**
//     * 缓存管理器
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        // Number of seconds before expiration. Defaults to unlimited (0)
//        cacheManager.setDefaultExpiration(expireTime);// Sets the default expire time (in seconds)
//        return cacheManager;
//    }
//
//    /**
//     * @return 自定义策略生成的key
//     * @description 自定义的缓存key的生成策略</   br>
//     * 若想使用这个key</br>
//     * 只需要讲注解上keyGenerator的值设置为customKeyGenerator即可</br>
//     */
//    @Bean
//    public KeyGenerator customKeyGenerator() {
//        return (o, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(o.getClass().getName());
//            sb.append(method.getName());
//            for (Object obj : objects) {
//                sb.append(obj.toString());
//            }
//            return sb.toString();
//        };
//    }
//
//}