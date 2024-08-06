package com.riwi.controllers;

import com.riwi.entities.StudentEntity;
import com.riwi.models.StudentModel;
import enums.EnumStatus;

import java.util.List;

public class StudentController {
    StudentModel studentModel;

    public StudentController(){
        this.studentModel= new StudentModel();
    }
    public StudentEntity create(String name, String lastName, String email, EnumStatus status, int document){
        return  this.studentModel.create(new StudentEntity(name,lastName,email,status,document));
    }
    public  Object read(int idStudent){
        return  this.studentModel.read(idStudent);
    }
    public  boolean delete(int idStudent){
        return this.studentModel.delete(idStudent);
    }

    public StudentEntity update(StudentEntity entity, Integer id){
        return  this.studentModel.update(entity,id);
    }
    public List<StudentEntity> readAll(int size, int numberPage) {
        return this.studentModel.readAll(size, numberPage);
    }
    public Object readEmail(String email){
        return  this.studentModel.readEmail(email);
    }
}
