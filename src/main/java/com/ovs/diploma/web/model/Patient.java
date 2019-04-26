package com.ovs.diploma.web.model;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<Reception> receptions = new HashSet<Reception>(0);
    private Set<Recipe> recipes;

    public Patient(String username) {
        this.username = username;
        this.firstName = "";
        this.secondName = "";
        this.birthday = "";
        this.sex = true;
        this.mobilePhone = "+380";
    }

    public Patient() {
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

    @Column(name = "birthday",  nullable = false, length = 45)
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

    @Column(name = "mobilephone", nullable = false, length = 45)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    public Set<Reception> getReceptions() {
        return this.receptions;
    }

    public void setReceptions(Set<Reception> receptions) {
        this.receptions = receptions;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", receptions=" + receptions +
                '}';
    }
}
