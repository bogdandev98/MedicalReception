package com.ovs.diploma.web.controller;

import com.ovs.diploma.web.dao.PatientDetailsDao;
import com.ovs.diploma.web.model.Patient;
import com.ovs.diploma.web.model.Reception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    PatientDetailsDao patientDetailsDao;

    @RequestMapping(value={"/patients"})
    public ModelAndView patientsMapping(){
        ModelAndView model = new ModelAndView("ListPatients");
        List<Patient> patientList = new ArrayList<>();
        for (Patient patient: patientDetailsDao.getNotExaminationPatients()){
            for (Reception reception: patient.getReceptions())
                if (!reception.isExamination())
                    patientList.add(patient);
        }
        model.addObject("patients", patientList);
        return model;
    }

    @RequestMapping("/{id}")
    public ModelAndView patientMapping(@PathVariable("id") String id){
        ModelAndView model = new ModelAndView("Patient");
        Patient patient = patientDetailsDao.findPatientByUsername(id);
        model.addObject("patient", patient);
        return model;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView patientEdit(@PathVariable("id")String id ){
        ModelAndView modelAndView = new ModelAndView("edit");
        Patient patient =  patientDetailsDao.findPatientByUsername(id);
        modelAndView.addObject("patientForm",patient);
        return modelAndView;
    }


    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("patientForm") Patient patient,
                                   BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println(patient.getUsername());
        String username=patient.getUsername();
        //patient.setUsername(username);
        if (result.hasErrors()) {
            //String username=SecurityContextHolder.getContext().getAuthentication().getName();
            //model.addAttribute("patientForm", patientDetailsDao.findPatientByUsername(username));
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

            patientDetailsDao.savePatient(patient);
            return "redirect:/nurse/"+username ;
        }

    }

}
