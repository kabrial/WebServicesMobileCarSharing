package com.carSharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.carSharing.model.Excursion;
import com.carSharing.model.Trip;
import com.carSharing.model.User;
import com.carSharing.service.ExcursionService;
import com.carSharing.service.TripService;

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
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String display(@PathVariable Long id, Model model){
        List<Trip> listTrip = tripService.findAllById(id);
        model.addAttribute("listTrip",listTrip);
        return "trip";
    }
    
    
    @RequestMapping(value="/createTrips", method = RequestMethod.GET)
    public String displayCreate(Model model){
        return "createTrip";
    }
}
