package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.ExcursionGroup;
import com.carSharing.model.ExcursionGroupPK;

public interface ExcursionGroupRepository extends JpaRepository<ExcursionGroup, ExcursionGroupPK> {

}
