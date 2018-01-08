package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.form.ExcursionForm;
import com.carSharing.model.Excursion;
import com.carSharing.repository.ExcursionRepository;
import com.carSharing.service.ExcursionService;


@Service
public class ExcursionServiceImpl implements ExcursionService {

    // Repository
    final ExcursionRepository excursionRepository;
    
    // Contructor
    public ExcursionServiceImpl(ExcursionRepository excursionRepository) {
        this.excursionRepository = excursionRepository;
    }
    
    public void save(ExcursionForm excursionForm) {
        excursionForm.setDays(excursionForm.getDays());
        excursionForm.setName(excursionForm.getName());
    }
    
    public List<Excursion> findAll() {
        return excursionRepository.findAll();
    }
    
}
