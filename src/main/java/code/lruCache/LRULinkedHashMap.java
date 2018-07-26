package code.lruCache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V>{

    private final int MAX_CACHE_SIZE;
    private final Lock lock = new ReentrantLock();

    public LRULinkedHashMap(int maxCapacity) {
        super((int)Math.ceil(maxCapacity/0.75F) + 1, 0.75f, true);
        MAX_CACHE_SIZE = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public V get(Object key)
    {
        try {
            lock.lock();
            return super.get(key);
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value)
    {
        try {
            lock.lock();
            return super.put(key, value);
        }
        finally {
            lock.unlock();
        }
    }
}
