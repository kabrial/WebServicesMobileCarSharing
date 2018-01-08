package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.model.Group;
import com.carSharing.repository.GroupRepository;
import com.carSharing.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	// Repository
	final GroupRepository groupRepository;

	// Contructor
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Group findOne(Long idGroup) {
		return groupRepository.findOne(idGroup);
	}

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}

}
