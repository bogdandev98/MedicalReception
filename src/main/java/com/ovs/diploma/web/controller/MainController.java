package com.ovs.diploma.web.controller;

import com.ovs.diploma.model.Patient;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value={"/doctor**", "/nurse/patients"})
    public ModelAndView patientsMapping(){
        ModelAndView model = new ModelAndView("ListPatients");
        model.addObject("patients", Patient.getListPatients());
        return model;
    }

    @RequestMapping("/patient")
    public ModelAndView patientMapping(){
        ModelAndView model = new ModelAndView("hello");
        return model;
    }

    @RequestMapping("/{id}")
    public ModelAndView patientMapping(@PathVariable("id") String id){
        ModelAndView model = new ModelAndView("Patient");
        for (Patient patient: Patient.getListPatients()){
            if (patient.getId().equals(id)) {
                model.addObject("patient", patient);
                System.out.println(patient.getFirstName());
            }
        }
        return model;
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", ((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage());
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }
    }
