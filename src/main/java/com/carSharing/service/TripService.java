package com.carSharing.service;

import java.util.List;

import com.carSharing.model.Trip;

public interface TripService {

    
    List<Trip> findAllById(Long id);
}
