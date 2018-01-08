package com.carSharing.service;

import java.util.List;

import com.carSharing.model.Excursion;

public interface ExcursionService {
    
    void save(Excursion excursion);
    
    List<Excursion> findAll();
}
