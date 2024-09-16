package main.cache.helpers;

// Separate Node class
public class Node<K> {
    public K key;
    Node<K> prev;
    Node<K> next;

    public Node(K key) {
        this.key = key;
    }
}

