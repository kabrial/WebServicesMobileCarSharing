package com.carSharing.service;

import com.carSharing.form.ReservedForm;

public interface TripChildService {

    void save(Long idExcursion, Long id, ReservedForm reserved);
}
