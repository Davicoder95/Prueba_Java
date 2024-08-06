package com.riwi.controllers;

import com.riwi.entities.InscriptionEntity;
import com.riwi.entities.QualificationEntity;
import com.riwi.models.QualificationModel;

public class QualificationController {

    QualificationModel qualificationModel;

    public QualificationController(){
        this.qualificationModel= new QualificationModel();
    }

    public QualificationEntity create(String description, Integer qualification,Integer idCourse, Integer idStudent){
        return  this.qualificationModel.create(new QualificationEntity(description,qualification,idCourse,idStudent));
    }

    public  Object read(int idQualification){
        return  this.qualificationModel.read(idQualification);
    }
    public  boolean delete(int idQualification){
        return this.qualificationModel.delete(idQualification);
    }

    public QualificationEntity update(QualificationEntity entity, Integer id){
        return  this.qualificationModel.update(entity,id);
    }
}
