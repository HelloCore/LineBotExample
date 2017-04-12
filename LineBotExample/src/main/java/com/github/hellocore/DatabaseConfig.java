package com.github.hellocore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.github.hellocore.model.User;

@Configuration
public class DatabaseConfig {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	
    @Bean
    public RedisTemplate<String, User> userTemplate() {
    	RedisTemplate<String, User> userTemplate = new RedisTemplate<>();
    	Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
    	userTemplate.setConnectionFactory(jedisConnectionFactory);
    	userTemplate.setDefaultSerializer(serializer);
    	userTemplate.setHashKeySerializer(serializer);
    	userTemplate.setHashValueSerializer(serializer);
        userTemplate.afterPropertiesSet();

        return userTemplate;
    }
}
