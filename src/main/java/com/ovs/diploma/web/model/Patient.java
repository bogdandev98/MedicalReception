package com.ovs.diploma.web.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    private String username;
    private String firstName;
    private String secondName;
    private String birthday;
    private boolean sex;
    private String mobilePhone;

    public Patient() {}

    public Patient(String username, String firstName, String secondName, String birthday) {
        this.username = username;
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

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "secondname", nullable = false, length = 45)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column(name = "birthday", unique = true, nullable = false, length = 45)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Column(name = "sex", nullable = false)
    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Column(name = "mobilephone", nullable = false)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
