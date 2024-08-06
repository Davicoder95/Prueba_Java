package com.riwi.persistence.iModel;

import com.riwi.entities.StudentEntity;
import com.riwi.persistence.crud.*;

public interface InterfaceStudentModel extends
        CreateModel<StudentEntity>,
        DeleteModel<Integer>,
        UpdateModel<StudentEntity, Integer>,
        ReadModel<String>,
        ReadAllModel<StudentEntity>,
        ReadIdCourse<Integer> {

}
