package software.ulpgc.is2.kata4.viewmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Histogram implements Iterable<Integer>{
    private final Map<Integer, Integer> map;

    public Histogram() {
        this.map = new HashMap<>();
    }

    public void put(int bin) {
        this.map.put(bin, count(bin)+1);
    }

    public int count(int bin) {
        return this.map.getOrDefault(bin,0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }
}
