package software.ulpgc.is2.kata4.viewmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Histogram implements Iterable<Integer>{
    private final Map<Integer, Integer> map;
    private Map<String, String> labels;

    public Histogram(HashMap<String, String> labels) {
        this.map = new HashMap<>();
        this.labels = labels;
    }

    public void put(int bin) {
        this.map.put(bin, count(bin)+1);
    }

    public int count(int bin) {
        return this.map.getOrDefault(bin,0);
    }

    public String title() {return labels.getOrDefault("title", "");}
    public String x() {return labels.getOrDefault("x", "");}
    public String y() {return labels.getOrDefault("y", "");}
    public String legend() {return labels.getOrDefault("legend", "");}

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }
}
