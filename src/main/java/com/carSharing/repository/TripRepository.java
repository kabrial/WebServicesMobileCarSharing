package com.carSharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carSharing.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByExcursionId(Long id);
}
