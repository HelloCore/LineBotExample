package com.github.hellocore.repository;

import java.util.List;

import com.github.hellocore.model.Group;

public interface GroupRepository {

	public void saveGroup(Group group);
	public List<Group> listGroup();
	
}
