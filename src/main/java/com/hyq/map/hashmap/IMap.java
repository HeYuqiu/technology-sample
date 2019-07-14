package com.hyq.map.hashmap;

public interface IMap<K, V> {
    void put(K key, V value);

    V get(K key);
}
