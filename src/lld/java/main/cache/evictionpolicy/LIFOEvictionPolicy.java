package main.cache.evictionpolicy;

import java.util.Stack;

public class LIFOEvictionPolicy<K> implements EvictionPolicy<K>{
    private Stack<K> stack = new Stack<>();
    @Override
    public void recordAccess(K key) {
        stack.push(key);
    }

    @Override
    public K evict() {
        return stack.pop();
    }
}
