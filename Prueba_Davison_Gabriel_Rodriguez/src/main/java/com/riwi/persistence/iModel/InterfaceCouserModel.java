package com.riwi.persistence.iModel;

import com.riwi.entities.CourseEntity;
import com.riwi.entities.StudentEntity;
import com.riwi.persistence.crud.*;

public interface InterfaceCouserModel extends
        CreateModel<CourseEntity>,
        DeleteModel<Integer>,
        UpdateModel<CourseEntity, Integer>,
        ReadModel<Integer>,
        ReadAllModel<CourseEntity> {
    Object read(Integer id);
}
