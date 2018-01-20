package com.carSharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.form.ReservedForm;
import com.carSharing.model.Child;
import com.carSharing.model.Excursion;
import com.carSharing.model.ExcursionGroup;
import com.carSharing.model.Group;
import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripParent;
import com.carSharing.model.User;
import com.carSharing.repository.ChildrenRepository;
import com.carSharing.repository.ExcursionRepository;
import com.carSharing.repository.TripChildRepository;
import com.carSharing.repository.TripParentRepository;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.ChildrenService;
import com.carSharing.service.TripChildService;
import com.carSharing.service.TripParentService;
import com.carSharing.service.TripService;
import com.carSharing.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Reserved Trip Controller
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Controller
@AllArgsConstructor
public class ReservedController {
    
    // Repository
    @Autowired
    private ChildrenService childrenService;
    
    @Autowired
    private TripService tripService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    ExcursionRepository excursionRepository;
    
    @Autowired
    TripParentService tripParentService;
    
    @Autowired
    TripChildService tripChildService;
    
    @Autowired
    TripParentRepository tripParentRepository;
    
    @Autowired
    TripRepository tripRepository;
    
    @Autowired
    TripChildRepository tripChildRepository;
    
    @Autowired
    ChildrenRepository childrenRepository;
    
    @RequestMapping(value = "/reserve/{idExcursion}/{id}", method = RequestMethod.GET)
    public String display(@PathVariable Long idExcursion, @PathVariable Long id, @ModelAttribute ReservedForm reserved, Model model) {

        model.addAttribute("theReserve", reserved);
        model.addAttribute("idExcursion", idExcursion);
        model.addAttribute("id", id);
        
        Trip trip = tripService.findOne(id);
        model.addAttribute("numberPlaces",trip.getNumberPlaces());
        model.addAttribute("placesReserved",trip.getPlacesReserved());
        Long numberPlaces = trip.getNumberPlaces();
        Long placesReserved = trip.getPlacesReserved();
        Long numberPlacesDispo = numberPlaces - placesReserved;
        model.addAttribute("numberPlacesDispo", numberPlacesDispo);
        
        if (numberPlacesDispo == 0) {
            return "redirect:/trips/" + idExcursion;
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Child> children = childrenService.findByParent(user);
        List<Child> childs = new ArrayList<>();
        Excursion ex = excursionRepository.findOne(idExcursion);
        List<ExcursionGroup> exGrp = ex.getExcursionGroups();
        List<Group> groups = new ArrayList<>();
        for (ExcursionGroup exg : exGrp) {
            groups.add(exg.getGroup());
        }

        for (Child child : children) {
            if (groups.contains(child.getGroup())) {
                childs.add(child);

            }
        }
       
        
        
        
        
        /* verifier si des personnes sont deja présentes dans la table TripChild et TripParent */
        
        /* Parent */ 
        
        Trip tripTestVerification = tripRepository.findOne(id);
        List<TripParent> tripParent = tripParentRepository.findByParentAndTrip(user, tripTestVerification);
        if (tripParent.isEmpty()) {
            model.addAttribute("parent", 0);
        } else {
            model.addAttribute("parent", 1);
        }
        
        /* Child */ 
        
        Trip tripTestVerificationChild = tripRepository.findOne(id);
        
        Long idTripForSearch = tripTestVerificationChild.getExcursion().getId();
        
        List<Trip> listAllTrip = tripRepository.findByExcursionId(idTripForSearch);
        List<TripChild> listTripChild = new ArrayList<>();
        for (int i = 0; i < listAllTrip.size(); i++){
            listTripChild = tripChildRepository.findByTrip(listAllTrip.get(i));
        }
        
        List<Child> childUser = childrenRepository.findByParent(user);
        
        for (int j = 0; j < childUser.size(); j++) {
            if (listTripChild.get(j).getChild().getId() == childUser.get(j).getId()) {
                childs.remove(listTripChild.get(j).getChild().getId());
            }
        }
        
        
        /* *********************************************************************************** */
        
        model.addAttribute("childs", childs);
  
        return "reserve";
    }
    
    @RequestMapping(value = "/reserve/{idExcursion}/{id}", method = RequestMethod.POST)
    public String displayPost(@PathVariable Long idExcursion, @PathVariable Long id, @ModelAttribute ReservedForm reserved, Model model) {
        
        model.addAttribute("theReserve", reserved);
        model.addAttribute("idExcursion", idExcursion);
        model.addAttribute("id", id);
        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        
        tripParentService.save(idExcursion, id, user, reserved);
        tripChildService.save(idExcursion, id, reserved);
        
        return "redirect:/trips/" + idExcursion;
    }
}
