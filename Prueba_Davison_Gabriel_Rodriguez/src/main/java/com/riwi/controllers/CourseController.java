package com.riwi.controllers;

import com.riwi.entities.CourseEntity;
import com.riwi.entities.StudentEntity;
import com.riwi.models.CourseModel;

import java.util.List;

public class CourseController {

    CourseModel courseModel;

    public CourseController(){
        this.courseModel = new CourseModel();
    }

    public CourseEntity create(String nameCourse){
        return this.courseModel.create(new CourseEntity(nameCourse));
    }
    public  Object read(int idCourse){
        return  this.courseModel.read(idCourse);
    }
    public  boolean delete(int idCourse){
        return this.courseModel.delete(idCourse);
    }

    public CourseEntity update(CourseEntity entity, Integer id){
        return  this.courseModel.update(entity,id);
    }
    public List<CourseEntity> readAll(int size, int numberPage) {
        return this.courseModel.readAll(size, numberPage);
    }
}
