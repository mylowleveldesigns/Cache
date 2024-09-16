package main.cache.evictionpolicy;

public class EvictionPolicyFactory<K> {
    public EvictionPolicy<K> getEvictionPolicy(String policyType) throws Exception {
        switch (policyType){
            case "LRU":
                return new LRUEvictionPolicy<>();
            case "LIFO":
                return new LIFOEvictionPolicy<>();
            case "LFU":
                return new LFUEvictionPolicy<>();
            default:
                throw new Exception("Invalid eviction policy type: " + policyType);
        }
    }
}
