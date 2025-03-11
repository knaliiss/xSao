package dev.knalis.xsao.utils;


import java.util.HashMap;
import java.util.List;

public interface IStorage<T> {

    default List<T> getList() {
        throw new UnsupportedOperationException("this operation is not supported");
    }

    void add(T obj);

    void remove(T obj);

    void clear();

    default void add(T value, List<T> list){
        throw new UnsupportedOperationException("this operation is not supported");
    }

    default HashMap<T, T> getMap(){
        throw new UnsupportedOperationException("this operation is not supported");
    }

}
