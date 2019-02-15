package com.ovs.diploma.web.model;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "receptions")
public class Reception {

    private Integer id;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private boolean examination;
    private boolean therapy;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_name", nullable = false)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_name", nullable = false)
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "examination", nullable = false)
    public boolean isExamination() {
        return examination;
    }

    public void setExamination(boolean examination) {
        this.examination = examination;
    }

    @Column(name = "therapy", nullable = false)
    public boolean isTherapy() {
        return therapy;
    }

    public void setTherapy(boolean therapy) {
        this.therapy = therapy;
    }

    public Reception(Patient patient, Doctor doctor, Date date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public Reception(){

    }
}
