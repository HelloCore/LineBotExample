package com.github.hellocore.repository;

import java.util.List;

import com.github.hellocore.model.User;

public interface UserRepository {
	
	public void saveUser(User user);
	public List<User> listUser();
	
}
