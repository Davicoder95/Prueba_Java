package com.riwi.entities;

import enums.EnumStatus;

public class StudentEntity {

    private  int idStudent;
    private String name;
    private String lastName;
    private String email;
    private EnumStatus status;
    private int document;

    //Constructores


    public StudentEntity(String name, String lastName, String email, EnumStatus status, int document) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.document = document;
    }

    public StudentEntity(int idStudent, String name, String lastName, String email, EnumStatus status, int document) {
        this.idStudent = idStudent;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.document = document;
    }

    public StudentEntity() {
    }

    // getters y setters

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public int getDocument() {
        return document;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
//tostriing

    @Override
    public String toString() {
        return "StudentEntity " +'\n' +
                "idStudent: "+ idStudent+ '\n'+
                "name:  " + name + '\n' +
                "lastName: " + lastName + '\n' +
                "email: " + email + '\n' +
                "status=" + status +'\n'+
                "document: " + document;
    }
}
