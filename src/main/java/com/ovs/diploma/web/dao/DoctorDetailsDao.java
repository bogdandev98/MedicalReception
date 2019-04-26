package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.Doctor;
import com.ovs.diploma.web.model.Recipe;

public interface DoctorDetailsDao {
    Doctor findDoctorByUsername(String username);
    String saveRecipe(Recipe recipe);
}
