package com.carSharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Child;
import com.carSharing.model.User;

public interface ChildrenRepository extends JpaRepository<Child, Long> {

	List<Child> findByParent(User parent);

}
