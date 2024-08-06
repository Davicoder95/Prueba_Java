package com.riwi.entities;

public class QualificationEntity {
    private int idQualification;
    private String description;
    private int qualification;
    private  int idCourse;
    private int idStudent;

    //constructor


    public QualificationEntity(String description, int qualification, int idCourse, int idStudent) {
        this.description = description;
        this.qualification = qualification;
        this.idCourse = idCourse;
        this.idStudent = idStudent;
    }

    public QualificationEntity(int idQualification, String description, int qualification, int idCourse, int idStudent) {
        this.idQualification = idQualification;
        this.description = description;
        this.qualification = qualification;
        this.idCourse = idCourse;
        this.idStudent = idStudent;
    }

    public QualificationEntity() {
    }

    public int getIdQualification() {
        return idQualification;
    }

    public String getDescription() {
        return description;
    }

    public int getQualification() {
        return qualification;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdQualification(int idQualification) {
        this.idQualification = idQualification;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public String toString() {
        return "QualificationEntity " +'\n' +
                "idQualification: " + idQualification +'\n' +
                "description: " + description + '\n' +
                "qualification: " + qualification +'\n' +
                "idCourse: " + idCourse +'\n' +
                "idStudent: " + idStudent ;
    }
}
