package com.riwi.persistence.crud;

import java.util.ArrayList;
import java.util.List;

public interface ReadAllModel<Entity> {
    public List<Entity> readAll(int size, int numberPage);
    //se recibira una entidad que se guardara en una lista , se recibe size y numbre page para la paginacion de la misma
}
