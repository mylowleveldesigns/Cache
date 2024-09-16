package main;

import main.cache.CacheService;
import main.cache.evictionpolicy.EvictionPolicy;
import main.cache.evictionpolicy.EvictionPolicyFactory;
import main.cache.storage.CacheStorage;
import main.cache.storage.MapBasedStorage;

public class main {
    public static void main(String[] args) {
        try {
            CacheStorage<String, String> storage = new MapBasedStorage<>();
            EvictionPolicy<String> evictionPolicy = new EvictionPolicyFactory().getEvictionPolicy("LRU");
            CacheService<String, String> cacheService = new CacheService<>(storage, evictionPolicy, 3);

            cacheService.put("key1", "value1");
            cacheService.put("key2", "value2");
            cacheService.put("key3", "value3");

            System.out.println(cacheService.get("key1")); // Access key1, so it's now most recently used

            // Cache is full now, adding new key-value pair will evict the least recently used
            cacheService.put("key4", "value4");

            // key2 should be evicted since it's least recently used
            System.out.println(cacheService.get("key2")); // Returns null
        } catch (Exception e) {
            System.out.println("Exception with msg - " + e.getMessage());
        }
    }

}
