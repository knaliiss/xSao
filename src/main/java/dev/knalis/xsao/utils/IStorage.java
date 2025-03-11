package dev.knalis.xsao.utils;

import java.util.LinkedList;

public interface IStorage<T> {

    LinkedList<T> getList();

    void add(T obj);

    void remove(T obj);

    void clear();
}
