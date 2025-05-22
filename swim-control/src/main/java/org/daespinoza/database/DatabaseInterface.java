package org.daespinoza.database;

import java.util.List;

public interface DatabaseInterface {
    @SuppressWarnings("unused")
    <T> List<T> getAll();
    <T> void insert(T item);
    <T> void update(T item);
    <T> void delete(T item);

}
