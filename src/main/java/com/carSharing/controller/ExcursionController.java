package com.carSharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.form.ExcursionForm;
import com.carSharing.model.Excursion;
import com.carSharing.model.ExcursionGroup;
import com.carSharing.model.ExcursionGroupPK;
import com.carSharing.model.Group;
import com.carSharing.model.User;
import com.carSharing.repository.ExcursionGroupRepository;
import com.carSharing.repository.GroupRepository;
import com.carSharing.service.ExcursionService;
import com.carSharing.service.GroupService;
import com.carSharing.service.UserService;

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
    UserService userService;
    
    @Autowired
    ExcursionGroupRepository excursionGroupRepository;
    
    @GetMapping
    public String display(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        model.addAttribute("userName",username);
        model.addAttribute("userRole",user.getUserrole());
        List<Excursion> listExcursion = excursionService.findAll();
        List<ExcursionForm> list=new ArrayList<>();
        for(Excursion ex: listExcursion){
            ExcursionForm exx=new ExcursionForm();
            exx.setId(ex.getId());
            exx.setDays(ex.getDays());
            exx.setName(ex.getName());
            exx.setGroups(new ArrayList<>());
            for(ExcursionGroup exgrop:ex.getExcursionGroups()){
                exx.getGroups().add(exgrop.getGroup().getName());  
            }
            list.add(exx);
        }
        model.addAttribute("listExcursion",list);
        return "excursion";
    }
    
    @RequestMapping(value="/createExcursions", method = RequestMethod.GET)
    public String displayCreate(@ModelAttribute ExcursionForm excursion, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        if (user.getUserrole().equals("USER")){
            return "redirect:/excursions";
        }
        model.addAttribute("userName",username);
        model.addAttribute("userRole",user.getUserrole());
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
            Group group= groupRepository.findOne(Long.parseLong(i));
            ExcursionGroupPK pK=new ExcursionGroupPK();
            pK.setIdExcursion(ex.getId());
            pK.setIdGroup(group.getId());
            exgrp.setId(pK);
            exgrp.setExcursion(ex);
            exgrp.setGroup(group);
            excursionGroupRepository.save(exgrp);
        }
        return "redirect:/excursions";
    }
    
}
