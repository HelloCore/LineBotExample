package com.github.hellocore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.github.hellocore.model.Group;
import com.github.hellocore.repository.GroupRepository;

@Repository
public class GroupRepositoryImpl implements GroupRepository{

    private static final String KEY = "Group";

    @Autowired
    private RedisTemplate<String, Group> groupTemplate;
    
    private HashOperations<String, String, Group> hashOps = null;
    
    @PostConstruct
    private void init() {
        hashOps = groupTemplate.opsForHash();
    }
    
	@Override
	public void saveGroup(Group group) {
		hashOps.put(KEY, group.getGroupId(), group);
		
	}

	@Override
	public List<Group> listGroup() {
		return new ArrayList<Group>(hashOps.entries(KEY).values());

	}

}
