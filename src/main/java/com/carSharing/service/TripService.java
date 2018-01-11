package com.carSharing.service;

import java.util.List;

import com.carSharing.form.TripForm;
import com.carSharing.model.Trip;
import com.carSharing.model.User;

public interface TripService {

    Trip save(Long id, TripForm trip, User user);
    
    List<Trip> findAllById(Long id);
}
