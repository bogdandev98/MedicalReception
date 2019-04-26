package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.Patient;
import com.ovs.diploma.web.model.Reception;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ReceptionDetailsDaoImp implements ReceptionDetailsDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void saveReception(Reception reception) {
        sessionFactory.getCurrentSession().saveOrUpdate(reception);
    }

//    @Transactional(readOnly=true)
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<Patient> getAllReceptions() {
//        return sessionFactory.getCurrentSession().createQuery("from Reception").list();
//    }
//
//
//
//    @Transactional(readOnly=true)
//    @SuppressWarnings("unchecked")
//    @Override
//    public Patient findReceptionByUsername(String username) {
//        List<Patient> patients ;
//        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where username= ?")
//                .setParameter(0, username);
//        patients = query.list();
//
//        if (patients.size() > 0) {
//            return patients.get(0);
//        } else {
//            return null;
//        }
//    }'
}
