package com.riwi.persistence.iModel;

import com.riwi.entities.InscriptionEntity;
import com.riwi.entities.QualificationEntity;
import com.riwi.persistence.crud.CreateModel;
import com.riwi.persistence.crud.DeleteModel;
import com.riwi.persistence.crud.ReadModel;
import com.riwi.persistence.crud.UpdateModel;

public interface InterfaceQualificationModel extends
        CreateModel<QualificationEntity>,
        DeleteModel<Integer>,
        UpdateModel<QualificationEntity, Integer>,
        ReadModel<Integer> {
    Object read(Integer id);
}
