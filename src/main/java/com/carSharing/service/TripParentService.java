package com.carSharing.service;

import com.carSharing.form.ReservedForm;
import com.carSharing.model.User;

public interface TripParentService {

    void save(Long idExcursion, Long id, User user, ReservedForm reserved);
    
}
