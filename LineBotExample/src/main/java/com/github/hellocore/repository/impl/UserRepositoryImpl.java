package com.github.hellocore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.github.hellocore.model.User;
import com.github.hellocore.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String KEY = "User";
    
    private RedisTemplate<String, User> redisTemplate;
    
    private HashOperations<String, String, User> hashOps = null;
    
    @Autowired
    private UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
    	this.redisTemplate = redisTemplate;
	}

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
    

	@Override
	public void saveUser(User user) {
		hashOps.put(KEY, user.getUserId(), user);		
	}

	@Override
	public List<User> listUser() {
		return new ArrayList<User>(hashOps.entries(KEY).values());
	}

}
