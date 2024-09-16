package main.cache.storage;

public interface CacheStorage<K, V> {
    void put(K key, V value);
    V get(K key);

    boolean containsKey(K key);

    void remove(K key);
}
