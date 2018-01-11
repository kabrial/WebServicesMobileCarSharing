package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.form.TripForm;
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

    public Trip save(Long id, TripForm trip, User user) {

        Trip tr = new Trip();
        Long idInit = (long) 0;
        Long idLast = tripRepository.getMaxId();
        if (idLast == null) {
            tr.setId(idInit);
        }
        else {
            tr.setId(idLast + 1);
        }
        Excursion excursion = excursionRepository.findOne(id);
        tr.setExcursion(excursion);
        tr.setParent(user);
        tr.setDateDepart(trip.getDateDepart());
        tr.setNumberPlaces(trip.getNumberPlaces());
        tr.setPlacesReserved(trip.getPlacesReserved());
        return tripRepository.save(tr);
    }

    public List<Trip> findAllById(Long id) {

        return tripRepository.findByExcursionId(id);
    }
}
