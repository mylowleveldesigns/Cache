package main.cache.evictionpolicy;

import main.cache.helpers.DoublyLinkedList;
import main.cache.helpers.Node;


import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final DoublyLinkedList<K> list;
    private final Map<K, Node<K>> nodeMap;

    public LRUEvictionPolicy() {
        this.list = new DoublyLinkedList<>();
        this.nodeMap = new HashMap<>();
    }

    @Override
    public void recordAccess(K key) {
        if (nodeMap.containsKey(key)) {
            list.moveToEnd(nodeMap.get(key));
        } else {
            Node<K> newNode = list.add(key);
            nodeMap.put(key, newNode);
        }
    }

    @Override
    public K evict() {
        Node<K> removedNode = list.removeFirst();
        if (removedNode != null) {
            nodeMap.remove(removedNode.key);
            return removedNode.key;
        }
        return null;
    }
}


