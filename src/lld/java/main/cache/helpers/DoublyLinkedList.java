package main.cache.helpers;

public class DoublyLinkedList<K> {
    private Node<K> head;
    private Node<K> tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    // Add a new node to the end of the list
    public Node<K> add(K key) {
        Node<K> newNode = new Node<>(key);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return newNode;
    }

    // Move a node to the end of the list
    public void moveToEnd(Node<K> node) {
        if (node == tail) {
            return; // Already at the end
        }
        // Remove node from its current position
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next; // Node is head
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev; // Node is tail
        }
        // Insert node at the end
        node.next = null;
        node.prev = tail;
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
    }

    // Remove the first node (least recently used)
    public Node<K> removeFirst() {
        if (head == null) {
            return null;
        }
        Node<K> removedNode = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // List is now empty
        }
        return removedNode;
    }
}

