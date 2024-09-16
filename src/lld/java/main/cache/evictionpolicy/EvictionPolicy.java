package main.cache.evictionpolicy;

public interface EvictionPolicy<K> {
    /***
     * record access to the param key
     * @param key
     */
    void recordAccess(K key);

    /***
     * Evict and return the key that should be removed
     * @return
     */
    K evict();
}
