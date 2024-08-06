package com.riwi.entities;

public class CourseEntity {

    // Instanciar atributos
    private  int idCourse;
    private String nameCourse;

    //constructores

    public CourseEntity() {
    }

    public CourseEntity( int idCourse, String nameCourse) {
        this.nameCourse = nameCourse;
        this.idCourse= idCourse;
    }

    public CourseEntity(String nameCourse) {
        this.nameCourse = nameCourse;
    }
//getters y setter

    public int getIdCourse() {
        return idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

//tostring


    @Override
    public String toString() {
        return "CourseEntity " +
                "idCourse: " + idCourse + '\n' +
                "nameCourse: " + nameCourse ;
    }
}
