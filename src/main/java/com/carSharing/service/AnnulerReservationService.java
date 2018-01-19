package com.carSharing.service;

import java.util.List;

import com.carSharing.model.Child;
import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripParent;
import com.carSharing.model.User;

public interface AnnulerReservationService {

    List<TripParent> recupererListParent(User user);
    
    List<TripChild> recupererListChild(Trip id);
    
    List<TripChild> recupererListChildren(Child child);
    
    Trip findOne(Long id);
    
    void delete(Long id, User user);
}
