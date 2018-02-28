package com.uterm.service;

/**
 * CrudService
 * 
 * basic methods for accessing database
 */
public interface CrudService<T, ID> {
    public T add(T obj);
    public T get(ID id);
}