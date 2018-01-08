package com.carSharing.service;

import java.util.List;

import com.carSharing.model.Trip;
import com.carSharing.model.User;

public interface TripService {

    void save(Long id, Trip trip, User user);
    
    List<Trip> findAllById(Long id);
}
