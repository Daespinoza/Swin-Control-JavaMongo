package org.daespinoza.database;

import java.util.List;

public interface DatabaseInterface {
    @SuppressWarnings("unused")
    <T> List<T> getAll();

}
