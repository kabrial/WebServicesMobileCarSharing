package com.carSharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Child;
import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripChildPK;

public interface TripChildRepository extends JpaRepository<TripChild, TripChildPK> {

	List<TripChild> findByTrip(Trip id);

	List<TripChild> findByTripAndChild(Trip theTrip, Child child);

}
