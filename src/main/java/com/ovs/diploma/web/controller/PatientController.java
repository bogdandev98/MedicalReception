package com.ovs.diploma.web.controller;

import com.ovs.diploma.web.dao.PatientDetailsDao;
import com.ovs.diploma.web.dao.ReceptionDetailsDao;
import com.ovs.diploma.web.model.Patient;
import com.ovs.diploma.web.model.Reception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientDetailsDao patientDetailsDao;

    @Autowired
    ReceptionDetailsDao receptionDetailsDao;

    @RequestMapping("/homepage")
    public String patientMapping(Model model){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient= patientDetailsDao.findPatientByUsername(username);
        if (patient !=null){
            model.addAttribute("patient",patient);
            return "Patient";
        }else {
            patient = new Patient(username);
            model.addAttribute("patientForm",patient);
            return "edit";
        }
    }

    @RequestMapping("/edit")
    public ModelAndView patientEdit(){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient= patientDetailsDao.findPatientByUsername(username);
        ModelAndView model = new ModelAndView("edit");
        model.addObject("patientForm",patient);
        return model;
    }


    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("patientForm") Patient patient,
                                   BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println(patient.getFirstName());
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        patient.setUsername(username);
        if (result.hasErrors()) {
            //String username=SecurityContextHolder.getContext().getAuthentication().getName();
            //model.addAttribute("patientForm", patientDetailsDao.findPatientByUsername(username));
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

            patientDetailsDao.savePatient(patient);
            return "redirect:/patient/homepage" ;
        }

    }

    @RequestMapping(value = "/reception")
    public String reception(Model model){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient= patientDetailsDao.findPatientByUsername(username);
        Reception reception = new Reception(patient);
        model.addAttribute("receptionForm", reception);
        return "reception";
    }

    @RequestMapping(value ="/saveReception")
    public String saveReception(@ModelAttribute("receptionForm") Reception reception,
                                BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            //String username=SecurityContextHolder.getContext().getAuthentication().getName();
            //model.addAttribute("patientForm", patientDetailsDao.findPatientByUsername(username));
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

            receptionDetailsDao.saveReception(reception);
            return "redirect:/patient/homepage" ;
        }

    }

}
