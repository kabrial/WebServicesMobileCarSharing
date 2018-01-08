package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.model.Excursion;
import com.carSharing.model.Trip;
import com.carSharing.model.User;
import com.carSharing.repository.ExcursionRepository;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.TripService;


@Service
public class TripServiceImpl implements TripService {

    // Repository
    final TripRepository tripRepository;
    final ExcursionRepository excursionRepository;
    
    // Contructor
    public TripServiceImpl(TripRepository tripRepository, ExcursionRepository excursionRepository) {
        this.tripRepository = tripRepository;
        this.excursionRepository = excursionRepository;
    }
    
    public void save(Long id, Trip trip, User user) {
        Long idInit = (long) 0;
        Long idLast = tripRepository.getMaxId();
        if (idLast == null){
            trip.setId(idInit);
        } else {
            trip.setId(idLast+1);
        }
        Excursion excursion = excursionRepository.findOne(id);
        trip.setExcursion(excursion);
        trip.setParent(user);
        tripRepository.save(trip);
    }
    
    public List<Trip> findAllById(Long id) {
        return tripRepository.findByExcursionId(id);
    }
}
