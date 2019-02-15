package com.ovs.diploma.web;

import com.ovs.diploma.web.dao.PatientDetailsDao;
import com.ovs.diploma.web.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PatientFormValidator implements Validator {

    @Autowired
    PatientDetailsDao patientDetailsDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Patient patient = (Patient) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondName", "NotEmpty.userForm.secondName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "NotEmpty.userForm.birthday");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilePhone", "NotEmpty.userForm.mobilePhone");


        if (patient.getFirstName() == null || patient.getFirstName().equals("")) {
            errors.rejectValue("firstName", "NotEmpty.userForm.firstName");
        }
        if (patient.getSecondName() == null || patient.getSecondName().equals("")) {
            errors.rejectValue("secondName", "NotEmpty.userForm.secondName");
        }
        if (patient.getBirthday() == null || patient.getBirthday().equals("")) {
            errors.rejectValue("birthday", "NotEmpty.userForm.birthday");
        }
        if (patient.getFirstName() == null || patient.getFirstName().equals("")) {
            errors.rejectValue("mobilePhone", "NotEmpty.userForm.mobilePhone");
        }

    }
}
