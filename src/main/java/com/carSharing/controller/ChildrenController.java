package com.carSharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Trip Create Controller
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Controller
@RequestMapping(path="/children")
public class ChildrenController {

    @GetMapping
    public String displayCreate(Model model){
        return "children";
    }
}
