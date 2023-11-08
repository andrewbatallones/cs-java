package javaexamples;

import java.util.TreeMap;

public class TreeMapEx {
    public static void main(String[] args) {
        // We can kinda treat this as a minheap
        TreeMap<Integer, String> map = new TreeMap<>();

        // This will be updating the minheap
        map.put(1, "one");
        map.put(5, "five");
        map.put(3, "three");

        // This can poll the TreeMap to get the lowest (or highest if needed) key/value pair.
        System.out.println(map.pollFirstEntry().getValue());
        System.out.println(map.pollFirstEntry().getValue());

        // Add more to minheap
        map.put(2, "two");

        // Should be able to poll correctly.
        System.out.println(map.pollFirstEntry().getValue());
    }
}
