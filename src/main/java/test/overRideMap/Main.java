package test.overRideMap;

import test.overRideMap.Key;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Key, String> map = new HashMap<Key , String>();
        map.put(new Key(1), "result 1");
        map.put(new Key(2), "result 2");
        map.put(new Key(3), "result 3");
        map.put(new Key(4), "result 4");
        map.put(new Key(5), "result 5");
        map.put(new Key(6), "result 6");

        assert map.size() == 2;
        assert map.get(new Key(1)).equals("result 5");
        assert map.get(new Key(4)).equals("result 6");

        System.out.println(map.size());
        System.out.println(map.get(new Key(1)).equals("result 5"));
        System.out.println(map.get(new Key(4)).equals("result 6"));
    }

}