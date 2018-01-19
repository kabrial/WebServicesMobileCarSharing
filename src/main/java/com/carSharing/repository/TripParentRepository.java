package com.carSharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Trip;
import com.carSharing.model.TripParent;
import com.carSharing.model.TripParentPK;
import com.carSharing.model.User;

public interface TripParentRepository extends JpaRepository<TripParent, TripParentPK> {

	List<TripParent> findByTrip(Trip theTrip);

	List<TripParent> findByParent(User user);
}
