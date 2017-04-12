package com.github.hellocore;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.github.hellocore.model.Group;
import com.github.hellocore.model.User;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
public class LocalRedisConfig {


    @Bean
    public JedisConnectionFactory jedisConnectionFactory() throws URISyntaxException{
       URI redisUri = new URI(System.getenv("REDIS_URL"));
       JedisPoolConfig poolConfig = new JedisPoolConfig();
       poolConfig.setMaxTotal(10);
       poolConfig.setMinIdle(1);
       poolConfig.setMaxIdle(5);
       poolConfig.setTestOnBorrow(true);
       poolConfig.setTestOnReturn(true);
       poolConfig.setTestWhileIdle(true);
       JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
       jedisConnectionFactory.setHostName(redisUri.getHost());
       jedisConnectionFactory.setPort(redisUri.getPort());
       jedisConnectionFactory.setTimeout(Protocol.DEFAULT_TIMEOUT);
       jedisConnectionFactory.setPassword(redisUri.getUserInfo().split(":",2)[1]);
       return jedisConnectionFactory;
    }

	
    @Bean
    public RedisTemplate<String, User> userTemplate(JedisConnectionFactory jedisConnectionFactory) {
    	RedisTemplate<String, User> userTemplate = new RedisTemplate<>();
    	Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
    	userTemplate.setConnectionFactory(jedisConnectionFactory);
    	userTemplate.setHashValueSerializer(serializer);
        userTemplate.afterPropertiesSet();

        return userTemplate;
    }
    
    @Bean 
    public RedisTemplate<String, Group> groupTemplate(JedisConnectionFactory jedisConnectionFactory) {
    	RedisTemplate<String, Group> groupTemplate = new RedisTemplate<>();
    	Jackson2JsonRedisSerializer<Group> serializer = new Jackson2JsonRedisSerializer<>(Group.class);
    	groupTemplate.setConnectionFactory(jedisConnectionFactory);
    	groupTemplate.setHashValueSerializer(serializer);
    	groupTemplate.afterPropertiesSet();

        return groupTemplate;
    }

}