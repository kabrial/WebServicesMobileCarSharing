package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripChildPK;
import com.carSharing.model.TripParent;
import com.carSharing.model.TripParentPK;
import com.carSharing.model.User;
import com.carSharing.repository.TripChildRepository;
import com.carSharing.repository.TripParentRepository;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.AnnulerReservationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnnulerReservationServiceImpl implements AnnulerReservationService {

    @Autowired
    final TripChildRepository tripChildRepository;

    @Autowired
    final TripParentRepository tripParentRepository;

    @Autowired
    final TripRepository tripRepository;

    public List<TripParent> recupererListParent(User user) {

        return tripParentRepository.findByParent(user);
    }

    public Trip findOne(Long id) {

        return tripRepository.findOne(id);
    }

    public List<TripChild> recupererListChild(Trip id) {

        return tripChildRepository.findByTrip(id);

    }

    public void delete(Long id, User user) {

        TripParentPK tripParentPK = new TripParentPK();
        tripParentPK.setIdParent(user.getId());
        tripParentPK.setIdTrip(id);
        
        TripParent tripParentTestNull = tripParentRepository.findOne(tripParentPK);
        if (tripParentTestNull != null) {
        tripParentRepository.delete(tripParentPK);
        }

        
        Trip trip = tripRepository.findOne(id);
 
        List<TripChild> listTripChild = recupererListChild(trip);

        if (!listTripChild.isEmpty()) {
            for (int i = 0; i < listTripChild.size(); i++) {
                TripChildPK tripChildPK = new TripChildPK();
                tripChildPK.setIdChild(listTripChild.get(0).getChild().getId());
                tripChildPK.setIdTrip(trip.getId());
                
                TripChild tripChildTestNull = tripChildRepository.findOne(tripChildPK);
                if (tripChildTestNull != null) {
                tripChildRepository.delete(tripChildPK);
                }
            }
        }

        trip.setPlacesReserved(trip.getPlacesReserved()-(1+listTripChild.size()));
        tripRepository.save(trip);
    }

}
