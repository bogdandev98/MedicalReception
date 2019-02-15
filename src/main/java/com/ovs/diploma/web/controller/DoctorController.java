package com.ovs.diploma.web.controller;

import com.ovs.diploma.web.dao.PatientDetailsDao;
import com.ovs.diploma.web.model.Patient;
import com.ovs.diploma.web.model.Reception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    PatientDetailsDao patientDetailsDao;

    @RequestMapping(value={"/patients"})
    public ModelAndView patientsMapping(){
        ModelAndView model = new ModelAndView("ListPatients");
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        List<Patient> patientList = new ArrayList<>();
        for (Patient patient: patientDetailsDao.getAllPatients()){
            for (Reception reception: patient.getReceptions()){
                if(reception.getDoctor().getUsername().equals(username))
                    patientList.add(patient);
            }
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

}
