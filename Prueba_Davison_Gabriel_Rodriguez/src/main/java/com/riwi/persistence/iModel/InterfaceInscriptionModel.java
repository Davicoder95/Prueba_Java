package com.riwi.persistence.iModel;

import com.riwi.entities.InscriptionEntity;
import com.riwi.persistence.crud.CreateModel;
import com.riwi.persistence.crud.DeleteModel;
import com.riwi.persistence.crud.ReadModel;
import com.riwi.persistence.crud.UpdateModel;

public interface InterfaceInscriptionModel extends
        CreateModel<InscriptionEntity>,
        DeleteModel<Integer>,
        UpdateModel<InscriptionEntity, Integer>,
        ReadModel<Integer>
{
    Object read(Integer id);
}
