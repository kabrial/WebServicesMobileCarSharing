package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
