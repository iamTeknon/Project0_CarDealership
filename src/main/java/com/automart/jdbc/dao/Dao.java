package com.automart.jdbc.dao;

import java.util.Optional;

// The following code was borrowed from Hiram Kamau and modified for this project
// https://stackabuse.com/working-with-postgresql-in-java/
public interface Dao<T, I>{
    Optional<T> get(int id);
    Optional<I> save(T t);
    void update(T t);
    void delete(T t);
    Optional<T> getEverything();
}