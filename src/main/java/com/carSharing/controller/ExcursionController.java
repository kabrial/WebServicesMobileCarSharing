package com.carSharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.form.ExcursionForm;
import com.carSharing.model.Excursion;
import com.carSharing.model.ExcursionGroup;
import com.carSharing.model.Group;
import com.carSharing.repository.ExcursionGroupRepository;
import com.carSharing.repository.GroupRepository;
import com.carSharing.service.ExcursionService;
import com.carSharing.service.GroupService;

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
    @Autowired
    GroupService groupService;
    
    @Autowired
    GroupRepository groupRepository;
    
    @Autowired
    ExcursionGroupRepository excursionGroupRepository;
    
    @GetMapping
    public String display(Model model){
        List<Excursion> listExcursion = excursionService.findAll();
        model.addAttribute("listExcursion",listExcursion);
        return "excursion";
    }
    
    @RequestMapping(value="/createExcursions", method = RequestMethod.GET)
    public String displayCreate(@ModelAttribute ExcursionForm excursion, Model model){
        model.addAttribute("theExcursion", excursion);
        model.addAttribute("groups", groupService.findAll());
        return "createExcursion";
    }
    
    @RequestMapping(value = "/createExcursions", method = RequestMethod.POST)
    public String updateCreate(@ModelAttribute ExcursionForm excursion, Model model) {
        model.addAttribute("theExcursion", excursion);
        System.err.println(excursion.getGroups());
        Excursion ex=excursionService.save(excursion);
        for(String i : excursion.getGroups()){
            ExcursionGroup exgrp = new ExcursionGroup();
            System.err.println("ICI"+Long.parseLong(i));
            Group group= groupRepository.findOne(Long.parseLong(i));
            exgrp.setExcursion(ex);
            exgrp.setGroup(group);
            excursionGroupRepository.save(exgrp);
        }
        return "redirect:/excursions";
    }
    
}
