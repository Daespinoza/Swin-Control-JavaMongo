package org.daespinoza.database;

import org.daespinoza.model.Professor;
import org.daespinoza.model.Swimmer;
import org.daespinoza.model.SwimmingLesson;
import org.daespinoza.model.Transaction;

import java.util.List;

public interface DatabaseInterface {
    @SuppressWarnings("unused")
    <T> void getAll();

    <T> void insert(T item);
    <T> void update(T item);
    <T> void delete(T item);

}
