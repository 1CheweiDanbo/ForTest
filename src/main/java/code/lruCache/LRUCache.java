package code.lruCache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V>{
    private int cacheSize;

    public LRUCache(int cacheSize){
        //true for LRU,false for false
        //根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容
       super((int)Math.ceil(cacheSize/0.75) + 1, 0.75f,true);
       this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size()>cacheSize;
    }

    public static void main(String[] args) {
        /**
         * 使用它：LRUCache cache = Collections.synchronizedMap（new LRUCache(10, 10)）。
         * 这样cache就可以在多线程下执行get\put等操作了，但是，用这种方式得到的cache在多线程遍历时还是不安全的。
         * 所以不能在多线程下遍历cache，官方文档也建议在遍历synchronizedmap时使用map本身做同步
         */
        LRUCache<Integer,Integer> map = (LRUCache) Collections.synchronizedMap(new LRUCache(4));
        map.put(1,1);
        map.put(2,2);
        map.put(1,2);
        map.put(4,4);
        map.put(5,5);
        map.put(6,6);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
