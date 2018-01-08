package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.model.Trip;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.TripService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    // Repository
    final TripRepository tripRepository;
    
    public List<Trip> findAllById(Long id) {
        return tripRepository.findByExcursionId(id);
    }
}
