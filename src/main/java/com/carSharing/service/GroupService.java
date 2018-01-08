package com.carSharing.service;

import java.util.List;

import com.carSharing.model.Group;

public interface GroupService {

	Group findOne(Long idGroup);

	List<Group> findAll();

}
