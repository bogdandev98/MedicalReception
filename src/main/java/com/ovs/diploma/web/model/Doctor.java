package com.ovs.diploma.web.model;

import com.ovs.diploma.security.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    private String username;
    private String firstName;
    private String secondName;
    private String birthday;
    private boolean sex;
    private String mobilePhone;
    private Set<Reception> receptions;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    public Set<Reception> getReceptions() {
        return this.receptions;
    }

    public void setReceptions(Set<Reception> receptions) {
        this.receptions = receptions;
    }
}
