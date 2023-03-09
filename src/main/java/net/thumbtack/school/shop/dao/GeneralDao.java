package net.thumbtack.school.shop.dao;

import java.util.List;

public interface GeneralDao <T>{
    T findById(String id);
    List<T> findAll();
    void insert(T obj);
}
