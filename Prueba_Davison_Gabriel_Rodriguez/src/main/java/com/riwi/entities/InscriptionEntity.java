package com.riwi.entities;

public class InscriptionEntity {
    private int idInscription;
    private int idStudent;
    private int idCourse;

    public InscriptionEntity() {
    }

    public InscriptionEntity(int idStudent, int idCourse) {
        this.idStudent = idStudent;
        this.idCourse = idCourse;
    }

    public InscriptionEntity(int idInscription, int idStudent, int idCourse) {
        this.idInscription = idInscription;
        this.idStudent = idStudent;
        this.idCourse = idCourse;
    }

    public int getIdInscription() {
        return idInscription;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public String toString() {
        return "InscriptionEntity " +'\n'+
                "idInscription: " + idInscription +'\n'+
                "idStudent: " + idStudent +'\n'+
                "idCourse: " + idCourse ;
    }
}
