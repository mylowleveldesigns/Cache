package main.cache.evictionpolicy;

import java.util.HashMap;
import java.util.Map;

public class LFUEvictionPolicy<K> implements EvictionPolicy<K> {
    private Map<K, Integer> frequencyMap = new HashMap<>();

    @Override
    public void recordAccess(K key) {
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
    }

    @Override
    public K evict() {
        K leastFrequentlyUsed = null;
        int minFrequency = Integer.MAX_VALUE;
        for (Map.Entry<K, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() < minFrequency) {
                leastFrequentlyUsed = entry.getKey();
                minFrequency = entry.getValue();
            }
        }
        frequencyMap.remove(leastFrequentlyUsed);
        return leastFrequentlyUsed;
    }
}
