package com.carSharing.controller;

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

import com.carSharing.model.Trip;
import com.carSharing.model.User;
import com.carSharing.service.TripService;
import com.carSharing.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Trip Controller
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path="/trips")
public class TripController {

    // Repository
    @Autowired
    private TripService tripService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String display(@PathVariable Long id, Model model){
        List<Trip> listTrip = tripService.findAllById(id);
        model.addAttribute("listTrip",listTrip);
        return "trip";
    }
    
   
    
    @RequestMapping(value="/createTrips/{id}", method = RequestMethod.GET)
    public String displayCreate(@PathVariable Long id, @ModelAttribute Trip trip, Model model){
        model.addAttribute("theTrip", trip);
        model.addAttribute("id", id);
        return "createTrip";
    }
    
    @RequestMapping(value = "/createTrips/{id}", method = RequestMethod.POST)
    public String updateCreate(@PathVariable Long id, @ModelAttribute Trip trip, Model model) {
        model.addAttribute("theTrip", trip);
        model.addAttribute("id", id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        
        User user = userService.findByUsername(username);
        tripService.save(id, trip, user);
        return "redirect:/trips/" + id;
    }
}
