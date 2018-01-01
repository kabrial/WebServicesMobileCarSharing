package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {

}
