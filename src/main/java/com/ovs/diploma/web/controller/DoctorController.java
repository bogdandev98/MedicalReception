package com.ovs.diploma.web.controller;

import com.ovs.diploma.web.dao.DoctorDetailsDao;
import com.ovs.diploma.web.dao.FileDetailsDao;
import com.ovs.diploma.web.dao.PatientDetailsDao;
import com.ovs.diploma.web.model.Patient;
import com.ovs.diploma.web.model.Reception;
import com.ovs.diploma.web.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    PatientDetailsDao patientDetailsDao;
    @Autowired
    FileDetailsDao fileDetailsDao;
    @Autowired
    DoctorDetailsDao doctorDetailsDao;

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
        model.addObject("files", fileDetailsDao.getAllFileByUsername(id));
        String doctorName=SecurityContextHolder.getContext().getAuthentication().getName();
        Recipe recipe = new Recipe();
        recipe.setPatient(patient);
        recipe.setDoctor(doctorDetailsDao.findDoctorByUsername(doctorName));
        recipe.setText("");
        model.addObject("recipe", recipe);
        return model;
    }

    @RequestMapping("/downloadFile/{fileId}")
    public void downloadFile(HttpServletResponse response, @PathVariable("fileId") int fileId) throws IOException {
        java.io.File file  = new java.io.File(fileDetailsDao.getFileById(fileId).getFilePath());
        if(!file.exists()){
            String errorMessage = "Sorry. Thefile you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetypeis not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimetype : "+mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/sendRecipe", method = RequestMethod.POST)
    public String sendRecipe(@ModelAttribute("recipe")Recipe recipe){
        doctorDetailsDao.saveRecipe(recipe);
        return "redirect:/doctor/"+recipe.getPatient().getUsername();
    }

}
