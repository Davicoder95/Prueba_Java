package com.riwi.controllers;

import com.riwi.entities.InscriptionEntity;
import com.riwi.entities.StudentEntity;
import com.riwi.models.InscriptionModel;

public class InscriptionController {

    InscriptionModel inscriptionModel;

    public  InscriptionController(){
        this.inscriptionModel= new InscriptionModel();
    }

    public InscriptionEntity create(Integer idStudent, Integer idCourse){
        return  this.inscriptionModel.create(new InscriptionEntity(idStudent,idCourse));
    }

    public  Object read(int idInscription){
        return  this.inscriptionModel.read(idInscription);
    }
    public  boolean delete(int idInscription){
        return this.inscriptionModel.delete(idInscription);
    }

    public InscriptionEntity update(InscriptionEntity entity, Integer id){
        return  this.inscriptionModel.update(entity,id);
    }

}
