package com.riwi.persistence.crud;

public interface UpdateModel<Entity,ID>{

    public Entity update(Entity request, ID id);// se hara el update pidiendo la entidad a la que se le hara el update y el id del mismo
}
