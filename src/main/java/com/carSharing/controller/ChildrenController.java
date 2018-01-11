package com.carSharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.form.ChildForm;
import com.carSharing.form.ExcursionForm;
import com.carSharing.model.Child;
import com.carSharing.model.User;
import com.carSharing.service.ChildrenService;
import com.carSharing.service.ExcursionService;
import com.carSharing.service.GroupService;
import com.carSharing.service.UserService;

/**
 * Trip Create Controller
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 */
@Controller
@RequestMapping(path = "/children")
public class ChildrenController {

    @Autowired
    private ChildrenService childrenService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @GetMapping
    public String display(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Child> children = childrenService.findByParent(user);
        List<ChildForm> childs = new ArrayList<>();
        for (Child child : children) {
            ChildForm childForm = new ChildForm();
            childForm.setId(child.getId());
            childForm.setGroupName(child.getGroup().getName());
            childForm.setName(child.getName());
            childs.add(childForm);
        }
        model.addAttribute("children", childs);
        return "children";
    }

    @RequestMapping(value = "/createChild", method = RequestMethod.GET)
    public String displayCreate(@ModelAttribute ChildForm childForm, Model model) {

        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("child", childForm);
        return "createChild";
    }

    @RequestMapping(value = "/deleteChild/{childId}", method = RequestMethod.GET)
    public String delete(@PathVariable Long childId, Model model) {

        childrenService.delete(childId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Child> children = childrenService.findByParent(user);
        model.addAttribute("children", children);
        return "redirect:/children";
    }

    @RequestMapping(value = "/createChild", method = RequestMethod.POST)
    public String registration(@ModelAttribute ChildForm childForm, Model model) {

        Child child = new Child();
        child.setGroup(groupService.findOne(childForm.getIdGroup()));
        child.setName(childForm.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        child.setParent(user);
        childrenService.save(child);
        List<Child> children = childrenService.findByParent(user);
        model.addAttribute("children", children);
        return "redirect:/children";
    }
}
