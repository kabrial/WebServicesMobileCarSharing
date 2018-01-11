package com.carSharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.form.TripForm;
import com.carSharing.model.Child;
import com.carSharing.model.Group;
import com.carSharing.model.Excursion;
import com.carSharing.model.ExcursionGroup;
import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripChildPK;
import com.carSharing.model.User;
import com.carSharing.repository.ChildrenRepository;
import com.carSharing.repository.ExcursionRepository;
import com.carSharing.repository.TripChildRepository;
import com.carSharing.service.ChildrenService;
import com.carSharing.service.TripService;
import com.carSharing.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Trip Controller
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 */
@Controller
@AllArgsConstructor
@RequestMapping(path = "/trips")
public class TripController {

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
    ChildrenRepository childrenRepository;
    @Autowired
    TripChildRepository tripChildRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String display(@PathVariable Long id, Model model) {

        List<Trip> listTrip = tripService.findAllById(id);
        model.addAttribute("listTrip", listTrip);
        return "trip";
    }

    @RequestMapping(value = "/createTrips/{id}", method = RequestMethod.GET)
    public String displayCreate(@PathVariable Long id, @ModelAttribute TripForm trip, Model model) {

        model.addAttribute("theTrip", trip);
        model.addAttribute("id", id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Child> children = childrenService.findByParent(user);
        List<Child> childs = new ArrayList<>();
        Excursion ex = excursionRepository.findOne(id);
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
        model.addAttribute("childs", childs);
        return "createTrip";
    }

    @RequestMapping(value = "/createTrips/{id}", method = RequestMethod.POST)
    public String updateCreate(@PathVariable Long id, @ModelAttribute TripForm trip, Model model) {

        model.addAttribute("theTrip", trip);
        model.addAttribute("id", id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        trip.setPlacesReserved(1 + trip.getChilds().size());
        User user = userService.findByUsername(username);
        Trip tr = tripService.save(id, trip, user);

        for (long idch : trip.getChilds()) {
            TripChild trch = new TripChild();
            TripChildPK pk = new TripChildPK();
            pk.setIdChild(idch);
            pk.setIdTrip(tr.getId());
            Child child = childrenRepository.findOne(idch);
            trch.setChild(child);
            trch.setTrip(tr);
            trch.setId(pk);
            tripChildRepository.save(trch);
        }

        return "redirect:/trips/" + id;
    }
}
