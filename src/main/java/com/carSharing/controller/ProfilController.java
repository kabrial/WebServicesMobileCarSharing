package com.carSharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.model.Role;
import com.carSharing.model.User;
import com.carSharing.service.RoleService;
import com.carSharing.service.UserService;

/**
 * Profil Controller
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Controller
@RequestMapping(path="/profil")
public class ProfilController {
    // Services.
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    // Variables
    final String ADMIN = "ADMIN";
    
    /**
     * dipslay ALL profil
     * @param model
     * @return String
     */
    @GetMapping
    public String listAllProfil(Model model){
        
        Iterable<User> listProfil = userService.findAllUsers();

        model.addAttribute("listProfil",listProfil);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        
        User user = userService.findByUsername(username);
        model.addAttribute("userName",username);
        model.addAttribute("userRole",user.getUserrole());
        if (user.getUserrole().equals(ADMIN)) {
            return "profil";
        } 
        
        return "redirect:/";
                
    }
    
    /**
     * userId
     * @param model
     * @param userId
     * @return String
     */
    @RequestMapping(value="/{userId}",  method={RequestMethod.GET})
    public String findProfilId(Model model, @PathVariable long userId){
        
        User profil = userService.findUser(userId);        
        model.addAttribute("theProfil", profil);
        
        Iterable<Role> listRole = roleService.findAllRoles();

        model.addAttribute("listRole",listRole);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        
        User user = userService.findByUsername(username);
        model.addAttribute("userName",username);
        model.addAttribute("userRole",user.getUserrole());
        
        if (userId == user.getId()) {
            return "redirect:/profil";
        }
        
        if (user.getUserrole().equals(ADMIN)) {
            return "updateProfil";
        }    
        
        return "redirect:/";
            
    }
    
    /**
     * userId
     * @param model
     * @param userId
     * @param user
     * @return String
     */
    @RequestMapping(value="/{userId}",  method={RequestMethod.POST})
    public String updProfil(Model model,@PathVariable long userId,@ModelAttribute User user){

        model.addAttribute("theProfil", user);      
        userService.updateUser(user,userId);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        
        User userCurrent = userService.findByUsername(username);
        model.addAttribute("userName",username);
        model.addAttribute("userRole",userCurrent.getUserrole());
        
        return "redirect:/profil";
        
    }
}

