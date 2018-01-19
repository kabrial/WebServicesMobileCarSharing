package com.carSharing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carSharing.form.ReservedForm;
import com.carSharing.model.Child;
import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripChildPK;
import com.carSharing.repository.ChildrenRepository;
import com.carSharing.repository.TripChildRepository;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.TripChildService;

@Service
public class TripChildServiceImpl implements TripChildService {

 // Repository
    final TripChildRepository tripChildRepository;
    final TripRepository tripRepository;
    final ChildrenRepository childRepository;

    // Contructor
    public TripChildServiceImpl(TripChildRepository tripChildRepository, TripRepository tripRepository, ChildrenRepository childRepository) {
        this.tripChildRepository = tripChildRepository;
        this.tripRepository = tripRepository;
        this.childRepository = childRepository;
    }
    
    
    
    public void save(Long idExcursion, Long id, ReservedForm reserved) {
        if (!reserved.getChilds().isEmpty()) {
            for (int i=0; i < reserved.getChilds().size(); i++){
            TripChild tripChildEntity = new TripChild();
            Trip trip = tripRepository.findOne(id);
            Child child = childRepository.findOne(reserved.getChilds().get(i));
            TripChildPK tripChildPK = new TripChildPK();
            tripChildPK.setIdChild(child.getId());
            tripChildPK.setIdTrip(trip.getId());
            tripChildEntity.setTrip(trip);
            tripChildEntity.setChild(child);
            tripChildEntity.setId(tripChildPK);
            tripChildRepository.save(tripChildEntity);
            }
            Trip tripFindOne = tripRepository.findOne(id);
            Long var = tripFindOne.getPlacesReserved();
            List<Long> presenceChild = reserved.getChilds();
            Integer size = presenceChild.size();
            Long varTotal = var + size; 
            tripFindOne.setPlacesReserved(varTotal);
            
            tripRepository.save(tripFindOne);
            
        }
    }
    
}
