package main.cache.storage;

import java.util.HashMap;
import java.util.Map;

public class MapBasedStorage<K, V> implements CacheStorage<K, V>{
    private final Map<K, V> storage = new HashMap<>();
    @Override
    public void put(K key, V value) {
        storage.put(key, value);
    }

    @Override
    public V get(K key) {
        return storage.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        return storage.containsKey(key);
    }

    @Override
    public void remove(K key) {
        storage.remove(key);
    }
}