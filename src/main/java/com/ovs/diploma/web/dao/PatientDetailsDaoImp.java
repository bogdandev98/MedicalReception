package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.Patient;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PatientDetailsDaoImp implements PatientDetailsDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    @Override
    public Patient findPatientByUsername(String username) {
        List<Patient> patients ;
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where username= ?")
                .setParameter(0, username);
        patients = query.list();

        if (patients.size() > 0) {
            return patients.get(0);
        } else {
            return null;
        }

    }

    @Transactional()
    @Override
    public String savePatient(Patient patient) {
        System.out.println(patient.getFirstName());
        sessionFactory.getCurrentSession().saveOrUpdate(patient);
        return null;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<Patient> getNotExaminationPatients() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Patient")
                .list();
    }

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Patient> getAllPatients() {
        return sessionFactory.getCurrentSession().createQuery("from Patient").list();
    }
    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Patient> getPatientsByDoctor(String username) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Patient")
                .setParameter(0, username)
                .list();
    }
}
