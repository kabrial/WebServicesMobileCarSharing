package com.carSharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.model.TripParent;
import com.carSharing.model.User;
import com.carSharing.repository.TripParentRepository;
import com.carSharing.service.AnnulerReservationService;
import com.carSharing.service.UserService;

/**
 * Annuler Reservation Controller
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 */
@Controller
@RequestMapping(path = "/annulerReservation")
public class AnnulerReservationController {
    
    @Autowired
    AnnulerReservationService annulerReservationService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    TripParentRepository tripParentRepository;
    
    
    @GetMapping
    public String display(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<TripParent> listParent = annulerReservationService.recupererListParent(user);
        
        model.addAttribute("listParent", listParent);
        
        return "annulerReservation";
    }
    
    
    
    @RequestMapping(value = "/deleteReservation/{id}", method = RequestMethod.GET)
    public String displayList(@PathVariable Long id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        
        annulerReservationService.delete(id, user);
        
        return "redirect:/annulerReservation";
    }
}
