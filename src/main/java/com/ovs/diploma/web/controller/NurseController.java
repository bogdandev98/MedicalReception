package com.ovs.diploma.web.controller;

import com.ovs.diploma.web.dao.FileDetailsDao;
import com.ovs.diploma.web.dao.PatientDetailsDao;
import com.ovs.diploma.web.model.File;
import com.ovs.diploma.web.model.Patient;
import com.ovs.diploma.web.model.Reception;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller()
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    PatientDetailsDao patientDetailsDao;

    @Autowired
    FileDetailsDao fileDetailsDao;

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
        model.addObject("files", fileDetailsDao.getAllFileByUsername(id));
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

    @RequestMapping(value = "/uploadFile/{username}", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("username")String username){
        System.out.println(file.getOriginalFilename());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        String currentDate = dateFormat.format(calendar.getTime());
        String filePath ="E:\\Java\\MyGame\\diploma\\src\\main\\resources\\file\\"+username+"_"+currentDate+file.getOriginalFilename().replace(" ", "_");
        try {
            file.transferTo(new java.io.File(filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
        File dbFile = new File();
        dbFile.setFileName(file.getOriginalFilename());
        dbFile.setFilePath(filePath);
        dbFile.setUsername(username);
        fileDetailsDao.saveFile(dbFile);
        return "redirect:/nurse/"+username;
    }

    @RequestMapping("/downloadFile/{fileId}")
    public void downloadFile(HttpServletResponse response, @PathVariable("fileId") int fileId) throws IOException {
        java.io.File file  = new java.io.File(fileDetailsDao.getFileById(fileId).getFilePath());
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimetype : "+mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
