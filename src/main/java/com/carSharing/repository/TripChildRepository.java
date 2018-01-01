package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.TripChild;
import com.carSharing.model.TripChildPK;

public interface TripChildRepository extends JpaRepository<TripChild, TripChildPK> {

}
