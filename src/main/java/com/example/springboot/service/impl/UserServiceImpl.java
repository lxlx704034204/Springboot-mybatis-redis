package com.example.springboot.service.impl;

import com.example.springboot.dao.UserMapper;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 写法连接：https://blog.csdn.net/u012343297/article/details/78840862
 */
@Service
//@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 获取用户策略：先从缓存中获取用户，没有则取数据表中
     * 数据，再将数据写入缓存
     * [注意：]
     *  1.@Cacheable 的key 要和 @CachePut 的key 一致
     *  2.@CachePut的  返回值 要和 @Cacheable 的返回值 一样，如果@Cacheable 返回的是一个对象，@CachePut 返回也要是对象，否则会报类型转换异常，如上代码 返回的都是 Uuser.
     *
     * @Cacheable 支持如下几个参数：
     * value：缓存的名称，在 spring 配置文件中定义，必须指定至少一个; value属性是必须指定的，其表示当前方法的返回值是会被缓存在哪个Cache上的，
     *        对应Cache的名称。其可以是一个Cache也可以是多个Cache，当需要指定多个Cache时其是一个数组。
     *          @Cacheable("cache1")            //Cache是发生在cache1上的
     *          @Cacheable({"cache1", "cache2"})//Cache是发生在cache1和cache2上的
     * key：缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     * condition：缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
     *
     * @CacheEvict  注解参数：
     * allEntries：true表示清除value中的全部缓存，默认为false
     * beforeInvocation： 清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     *
     * @Caching
     *
     *        @Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict。
     *
     *    @Caching(cacheable = @Cacheable("users"),
     *                          evict = { @CacheEvict("cache2"),
     *                          @CacheEvict(value = "cache3", allEntries = true) }
     *           )
     *    public User find(Integer id) {
     *       returnnull;
     *    }
     *
     */
    //http://www.cnblogs.com/ashleyboy/p/9595584.html
    //@Cacheable(cacheNames = "common1",key = "#root.methodName+'['+#id+']'")
    //@Cacheable(cacheNames = {"common1","product2"})// 默认key为参数，多个参数SimpleKey [arg1,arg2]
    //@Cacheable(cacheNames = "product",key = "#root.methodName+'['+#id+']'")
    //@Cacheable(cacheNames = "product",keyGenerator = "myKeyGenerator")
    //@Cacheable(cacheNames = "product",key = "#root.methodName+'['+#id+']'",condition="#a0>10",unless = "#a0==11") //或者condition="#id>10")
    @Cacheable(value = {"common1"}, key = "'ha1'+ T(String).valueOf(#p0)", condition = "#id lt 10")
    @Override
    public User getOne(Integer id) {
//        String key = "user_" + id;
//        ValueOperations<String, User> operations = redisTemplate.opsForValue();
//
//        boolean hasKey = redisTemplate.hasKey(key);
//        //缓存存在
//        if (hasKey) {
//            User user = operations.get(key);
//            System.out.println("==========从redis缓存中获得数据=========");
//            System.out.println(user.getName());
//            System.out.println("==============================");
//            return user;
//        }
        System.out.println("==========从数据表中获得数据=start========");

        User user = userMapper.getOne(id);
        System.out.println("==========从数据表中获得数据=start========");
        System.out.println(user.getName());
        System.out.println("==========从数据表中获得数据=end========");
//        //插入缓存
//        operations.set(key, user, 5, TimeUnit.MINUTES);
        return user;
    }

    //@CachePut将在执行完方法后判断unless，如果返回false，则放入缓存；（即跟condition相反）  , unless = "#result.name ne '李'"
    @CachePut(value = "common1", key = "'ha1'+ #user.id" )
    //@CachePut(value = "common", key = "#p0.getId()")
    //@CachePut(value="product",key = "#result.productId",condition = "#result!=null")
    @Override
    public User update(User user) {
        int update = userMapper.update(user);
        System.out.println("update 受影响条数："+update);
        //坑：注意这里的写法
        // fix: https://blog.csdn.net/dream_broken/article/details/72602218
        //      https://www.cnblogs.com/GH0522/p/9437080.html?utm_source=debugrun&utm_medium=referral
        return userMapper.getOne(user.getId());
    }


    @CacheEvict(value = "common1", key = "'ha1'+ T(String).valueOf(#p0)")
    //@CacheEvict(value="product",allEntries = true) //清楚所有缓存
    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }


    @CachePut(value = "common1", key ="'ha1'+ #p0.id")
    @Override
    public User insert(User user) {
        int insert = userMapper.insert(user);
        System.out.println("insert 受影响条数："+insert);
        return userMapper.getOne(user.getId());
    }


    //含有CachePut注解，所以执行这个方法时一定会查询数据库，及时有cacheable注解
    @Caching(
            cacheable = {@Cacheable(value="common1",key="#userName")},
            put = {
                    @CachePut(value="common1",key="T(String).valueOf(#result.id)"),
                    @CachePut(value="common1",key="#result.name")
            }
    )
    @Override
    public User getByName(String userName){
        User product =userMapper.getByName(userName);
        return product;
    }


    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

}
