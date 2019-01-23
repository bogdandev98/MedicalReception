package com.ovs.diploma.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Patient {

    public String id;

    private String firstName;

    private String secondName;

    private String birthday;

    public Patient() {

    }

    public Patient(String id, String firstName, String secondName, String birthday) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }

    public static List<Patient> getListPatients(){
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("1", "Bohdan","Ovsie","18.02"));
        patients.add(new Patient("2", "Bohda","Ovsien","18.02"));
        patients.add(new Patient("3", "Bohd","Ovsienk","18.02"));
        patients.add(new Patient("4", "Boh","Ovsienko","18.02"));
        return patients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return firstName;
    }
}
