package com.mes.dao;

public interface GenericDao<E> {
    public E create(E t);

    public E find(Object id);

    public E update(E t);

    public void delete(Object id);
}
