package com.carSharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Trip Search Controller
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Controller
@RequestMapping(path="/search")
public class TripSearchController {
    
    @GetMapping
    public String displaySearch(Model model){
        return "search";
    }
}
