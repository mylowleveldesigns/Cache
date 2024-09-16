package main.cache;

import main.cache.evictionpolicy.EvictionPolicy;
import main.cache.storage.CacheStorage;

public class CacheService<K, V> {
    private final CacheStorage<K, V> storage;
    private final EvictionPolicy<K> evictionPolicy;
    private final int capacity;
    private int currentSize;

    public CacheService(CacheStorage<K, V> storage, EvictionPolicy<K> evictionPolicy, int capacity) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
        this.currentSize = 0;
    }

    // Put a key-value pair in the cache
    public void put(K key, V value) {
        if (storage.containsKey(key)) {
            // If key already exists, update the value and refresh eviction policy
            storage.put(key, value);
            evictionPolicy.recordAccess(key);
            return;
        }

        // If the cache is at capacity, evict the least recently used item
        if (currentSize == capacity) {
            evict();
        }

        // Add the new key-value pair to storage and record access in the eviction policy
        storage.put(key, value);
        evictionPolicy.recordAccess(key);
        currentSize++;
    }

    // Get the value for a key
    public V get(K key) {
        if (!storage.containsKey(key)) {
            return null; // Return null if key is not found in cache
        }

        // Refresh the eviction policy and return the value
        evictionPolicy.recordAccess(key);
        return storage.get(key);
    }

    // Evict the least recently used (or the key based on eviction policy) from the cache
    public void evict() {
        K evictedKey = evictionPolicy.evict();
        if (evictedKey != null) {
            storage.remove(evictedKey);
            currentSize--;
        }
    }
}


