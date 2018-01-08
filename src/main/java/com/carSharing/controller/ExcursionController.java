package com.carSharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.model.Excursion;
import com.carSharing.service.ExcursionService;

import lombok.AllArgsConstructor;

/**
 * Excursion Controller
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path="/excursions")
public class ExcursionController {

    // Repository
    @Autowired
    private ExcursionService excursionService;
    
    @GetMapping
    public String display(Model model){
        List<Excursion> listExcursion = excursionService.findAll();
        model.addAttribute("listExcursion",listExcursion);
        return "excursion";
    }
    
    @RequestMapping(value="/createExcursions", method = RequestMethod.GET)
    public String displayCreate(@ModelAttribute Excursion excursion, Model model){
        model.addAttribute("theExcursion", excursion);
        return "createExcursion";
    }
    
    @RequestMapping(value = "/createExcursions", method = RequestMethod.POST)
    public String updateCreate(@ModelAttribute Excursion excursion, Model model) {
        model.addAttribute("theExcursion", excursion);
        excursionService.save(excursion);
        return "redirect:/excursions";
    }
    
}
