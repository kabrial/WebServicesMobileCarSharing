package com.carSharing.service;

import java.util.List;

import com.carSharing.form.ExcursionForm;
import com.carSharing.model.Excursion;

public interface ExcursionService {
    
    Excursion save(ExcursionForm excursionForm);
    
    List<Excursion> findAll();
}
