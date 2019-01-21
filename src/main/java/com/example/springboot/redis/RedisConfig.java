package com.example.springboot.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Redis缓存配置类   [去xml版本：  https://github.com/lxlx704034204/farm-service   pom: 1.5.10]
 * @author szekinwin
 *
 * RedisConfig  --->  JedisPoolConfig  <--- applicationContext-redis.xml
 *
 * applicationContext-redis.xml（spring整合redis集群） : https://www.cnblogs.com/northern-light/p/8540309.html
 *
 * SpringBoot整合 redis(各种模式)： https://blog.csdn.net/plei_yue/article/details/79362372
 *
 * 不错的配置博客：
 * 	http://www.cnblogs.com/ashleyboy/p/9595584.html
 *	https://blog.csdn.net/g1506490083/article/details/79436711  锁控制
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{


	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;

	//	    @Value("${spring.redis.password}")
	//	    private String password;

	@Bean
	public JedisPool redisPoolFactory() {
		System.out.println("JedisPool注入成功！！");
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		//本地redis未设置密码，所以第五个参数password不传
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		return jedisPool;
	}

	/**
	 * 缓存管理器.
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
		CacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return cacheManager;
	}

	/**
	 * redis模板操作类,类似于jdbcTemplate的一个类;
	 * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
	 * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
	 * 自己的缓存类，比如：RedisStorage类;
	 * @param redisConnectionFactory : 通过Spring进行注入，参数在application.properties进行配置；
	 * @return
	 *
	 *  重写Redis序列化方式，使用Json方式:
	 * 	当我们的数据存储到Redis的时候，我们的键（key）和值（value）都是通过Spring提供的Serializer序列化到数据库的。RedisTemplate默认使用的
	 * 	是JdkSerializationRedisSerializer，StringRedisTemplate默认使用的是StringRedisSerializer。
	 * 	Spring Data JPA为我们提供了下面的Serializer：
	 * 	GenericToStringSerializer、Jackson2JsonRedisSerializer、JacksonJsonRedisSerializer、JdkSerializationRedisSerializer、OxmSerializer、StringRedisSerializer。
	 *
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
				new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		// 设置值（value）的序列化采用Jackson2JsonRedisSerializer
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// 设置键（key）的序列化采用StringRedisSerializer。
		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * 自定义key生成策略
	 * 类名+方法名+参数(适用于分布式缓存)，默认key生成策略分布式下有可能重复被覆盖
	 * @return
	 * 详细配置： https://blog.csdn.net/litte_frog/article/details/79468270
	 */
	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		return (o, method, objects) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(method.getName() + "(");
			for (Object obj : objects) {
				sb.append(obj.toString());
			}
			sb.append(")");
			return sb.toString();
		};
	}


}
