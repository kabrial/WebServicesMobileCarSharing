package com.carSharing.serviceImpl;

import org.springframework.stereotype.Service;

import com.carSharing.form.ReservedForm;
import com.carSharing.model.Trip;
import com.carSharing.model.TripParent;
import com.carSharing.model.TripParentPK;
import com.carSharing.model.User;
import com.carSharing.repository.TripParentRepository;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.TripParentService;

@Service
public class TripParentServiceImpl implements TripParentService {

    // Repository
    final TripParentRepository tripParentRepository;
    final TripRepository tripRepository;

    // Contructor
    public TripParentServiceImpl(TripParentRepository tripParentRepository, TripRepository tripRepository) {
        this.tripParentRepository = tripParentRepository;
        this.tripRepository = tripRepository;
    }

    public void save(Long idExcursion, Long id, User user, ReservedForm reserved) {
        if (reserved.getPresenceParent() == 1) {
            TripParent tripParentEntity = new TripParent();
            Trip tripFindOne = tripRepository.findOne(id);
            TripParentPK tripParentPK = new TripParentPK();
            tripParentPK.setIdParent(user.getId());
            tripParentPK.setIdTrip(id);
            tripParentEntity.setId(tripParentPK);
            tripParentEntity.setParent(user);
            tripParentEntity.setTrip(tripFindOne);
            
            Long var = tripFindOne.getPlacesReserved();
            Long presenceParent = reserved.getPresenceParent();
            Long varTotal = var + presenceParent; 
            tripFindOne.setPlacesReserved(varTotal);
            
            tripRepository.save(tripFindOne);
            tripParentRepository.save(tripParentEntity);
        }
    }
}
