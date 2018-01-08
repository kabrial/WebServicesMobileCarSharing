package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.model.Child;
import com.carSharing.model.User;
import com.carSharing.repository.ChildrenRepository;
import com.carSharing.service.ChildrenService;

@Service
public class ChildrenServiceImpl implements ChildrenService {

	// Repository
	final ChildrenRepository childrenRepository;

	// Contructor
	public ChildrenServiceImpl(ChildrenRepository childrenRepository) {
		this.childrenRepository = childrenRepository;
	}

	@Override
	public void save(Child child) {
		childrenRepository.save(child);
	}

	@Override
	public List<Child> findByParent(User parent) {
		return childrenRepository.findByParent(parent);
	}

	@Override
	public void delete(Long id) {
		childrenRepository.delete(id);

	}

}
