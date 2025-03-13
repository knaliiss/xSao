package dev.knalis.xsao.interfaces;


import java.util.List;

public interface IStorage<T> {

    List<T> getList();

    void add(T obj);

    void remove(T obj);

    void clear();

}
