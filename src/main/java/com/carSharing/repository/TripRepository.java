package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
