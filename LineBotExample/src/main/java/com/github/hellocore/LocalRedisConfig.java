package com.github.hellocore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.github.hellocore.model.User;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class LocalRedisConfig {

    @Bean
    public RedisConnectionFactory jedisConnectionFactory(){
       JedisPoolConfig poolConfig = new JedisPoolConfig();
       poolConfig.setMaxTotal(10);
       poolConfig.setMinIdle(1);
       poolConfig.setMaxIdle(5);
       
       poolConfig.setTestOnBorrow(true);
       poolConfig.setTestOnReturn(true);
       poolConfig.setTestWhileIdle(true);
       JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
       return jedisConnectionFactory;
    }
    
    @Bean
    public RedisTemplate<String, User> userTemplate(RedisConnectionFactory redisConnectionFactory) {
    	RedisTemplate<String, User> userTemplate = new RedisTemplate<>();
    	Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
    	userTemplate.setConnectionFactory(redisConnectionFactory);
    	userTemplate.setDefaultSerializer(serializer);
    	userTemplate.setHashKeySerializer(serializer);
    	userTemplate.setHashValueSerializer(serializer);
        userTemplate.afterPropertiesSet();

        return userTemplate;
    }

}