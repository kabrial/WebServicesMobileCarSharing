package com.carSharing.service;

import java.util.List;

import com.carSharing.model.Child;
import com.carSharing.model.User;

public interface ChildrenService {

	void save(Child child);
	
	List<Child>findByParent(User parent);

	void delete(Long id);
}
