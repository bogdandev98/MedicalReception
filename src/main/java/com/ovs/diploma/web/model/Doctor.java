package com.ovs.diploma.web.model;

import com.ovs.diploma.security.model.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {

    private String username;
    private String firstName;
    private String secondName;
    private String birthday;
    private String resume;
    private Set<Reception> receptions = new HashSet<Reception>(0);

    public Doctor() {}

    public Doctor(String username, String firstName, String secondName, String birthday, String resume, Set<Reception> receptions) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.resume = resume;
        this.receptions = receptions;
    }

    public Doctor(String username, String firstName, String secondName, String birthday, String resume) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.resume = resume;
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

    @Column(name = "resume",  length = 150)
    public String getResume() {
        return this.resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    public Set<Reception> getReceptions() {
        return this.receptions;
    }

    public void setReceptions(Set<Reception> receptions) {
        this.receptions = receptions;
    }
}
