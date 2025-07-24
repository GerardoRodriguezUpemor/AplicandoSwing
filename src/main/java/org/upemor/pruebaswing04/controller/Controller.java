package org.upemor.pruebaswing04.controller;

import java.util.List;
import org.upemor.pruebaswing04.model.entity.Entity;
import org.upemor.pruebaswing04.model.repository.Repository;

/** @author cerimice **/

public abstract class Controller<R extends Repository<E>, E extends Entity> {

    protected R repository;

    protected abstract boolean validate(E obj) throws Exception;

    public boolean save(E obj) throws Exception {
        try {
            if (validate(obj)) {
                if (obj.getId() == 0)
                    return repository.create(obj);
                return repository.update(obj);
            }
            return false;
        } catch (Exception ex) {
            System.out.println(
                    "Error: " + ex.getMessage()
                            + " in method: save()"
                            + " in class: " + this.getClass().getName());
            throw ex;
        }
    }

    public List<E> getAll() throws Exception {
        try {
            return repository.readAll();
        } catch (Exception ex) {
            System.out.println(
                    "Error: " + ex.getMessage()
                            + " in method: getAll()"
                            + " in class: " + this.getClass().getName());
            throw ex;
        }
    }

}
