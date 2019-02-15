package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.Patient;

import java.util.List;

public interface PatientDetailsDao {
    Patient findPatientByUsername(String username);

    String savePatient(Patient patient);

    List<Patient> getAllPatients();

    List<Patient> getNotExaminationPatients();

    List<Patient> getPatientsByDoctor(String username);
}
