package com.mindex.challenge.service;

/**
 *
 * @author Forrest
 * @param <T>
 */
public interface DatabaseService<T> {
    T create(T t);
    T read(String id);
    T update(T t);
}
