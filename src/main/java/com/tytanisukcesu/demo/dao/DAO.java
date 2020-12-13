package com.tytanisukcesu.demo.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> getById(Long id);
    List<T> getAll();
    void create(T t);
    void update(T t);
    void delete(T t);

}
