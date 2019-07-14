package com.hyq.map.hashmap;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 手写一个hashMap
 */
public class HashMapDemo<K, V> implements IMap<K, V> {
    // 加载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;


    private Node<K, V>[] table;  // 用于存储当前数据的数组容器
    private int size; // 当前数据的长度

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    private void putVal(int hash, K key, V value) {
        if (table == null || table.length == 0) {
            resize();
        }
        int position = hash & table.length - 1; // 找到应该放到数组的哪个位置
        Node<K, V> positionNode = table[position];
        if (positionNode == null) {
            table[position] = new Node<>(hash, key, value, null);
        } else {
            for (int binCount = 0; ; binCount++) {
                Node<K, V> nextN = positionNode.nextNode;
                if (nextN == null) {
                    positionNode.nextNode = new Node<>(hash, key, value, null);
                    break;
                }
            }

        }

    }

    private Node<K, V>[] resize() {
        int oldCap = table == null ? 0 : table.length;
        Node<K, V>[] oldTab = table;
        int newCap;
        if (oldCap == 0) {
            newCap = DEFAULT_INITIAL_CAPACITY;
        } else {
            newCap = oldCap << 1;
        }
        Node<K, V>[] newTab = new Node[newCap];
        table = newTab;
        if (oldCap != 0) {
            for (int i = 0; i < oldCap; i++) {
                if (oldTab[i] != null) {
                    if (oldTab[i].nextNode == null) {

                    } else {
                        Node<K, V> temp;
                        for (temp = oldTab[i].nextNode; temp.nextNode != null; ) {
                            int position = temp.hash & newCap - 1; // 找到应该放到数组的哪个位置
                            Node<K, V> newNode = newTab[position];
                            if (newNode == null) {
                                newNode = new Node<>(temp.hash, temp.key, temp.value, null);
                            } else {
                                Node<K, V> tNode = newNode;
                                while ((tNode = tNode.nextNode) != null) {
                                }
                                tNode = new Node<>(temp.hash, temp.key, temp.value, null);
                            }

                            temp = temp.nextNode;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    static class Node<K, V> {
        private int hash;

        @Setter
        @Getter
        private K key;
        @Setter
        @Getter
        private V value;

        private Node nextNode;

        public Node(int hash, K key, V value, Node nextNode) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.nextNode = nextNode;
        }
    }


}
