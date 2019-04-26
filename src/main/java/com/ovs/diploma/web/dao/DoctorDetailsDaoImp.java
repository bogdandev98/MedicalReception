package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.Doctor;
import com.ovs.diploma.web.model.Recipe;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DoctorDetailsDaoImp implements DoctorDetailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    @Override
    public Doctor findDoctorByUsername(String username) {
        List<Doctor> doctors ;
        Query query = sessionFactory.getCurrentSession().createQuery("from Doctor where username= ?")
                .setParameter(0, username);
        doctors = query.list();

        if (doctors.size() > 0) {
            return doctors.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public String saveRecipe(Recipe recipe) {
        sessionFactory.getCurrentSession().saveOrUpdate(recipe);
        return null;
    }
}
