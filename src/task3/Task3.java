package task3;

import java.util.*;

public class Task3 {
    Set<Pair> connectionsSet;
    HashSet<TreeSet<Integer>> createdConnections;

    private Map<Integer, Integer> parent;
    private final int numberOfGraphs;

    public Task3(Set<Pair> connectionsSet) {
//        long startTime = System.currentTimeMillis();
        this.connectionsSet = connectionsSet;
        createdConnections = new HashSet<>();

        numberOfGraphs = findNumberOfSeparateGraphs(connectionsSet);
//        long endTime = System.currentTimeMillis();
//        System.out.println("\n\nDuration: " + (endTime - startTime) + " ms");

    }

    public int findNumberOfSeparateGraphs(Set<Pair> verticesSet) {
        parent = new HashMap<>();
        for (var twoVertices : verticesSet) {
            int left = find(twoVertices.getLeft());
            int right = find(twoVertices.getRight());
            parent.put(left, right);
        }

        return findAllSeparateGraphs();
    }

    int find(int point) {
        int p = parent.getOrDefault(point, point);
        if (p != point)
            p = find(p);
        parent.put(point, p);
        return p;
    }

    private int findAllSeparateGraphs() {
        int separateGraphs = 0;
        for (var k : parent.keySet())
            if (find(k) == k)
                separateGraphs += 1;
        return separateGraphs;
    }

    public int getNumberOfGraphs() {
        return numberOfGraphs;
    }

    public void printResults() {
        System.out.println(getNumberOfGraphs());
    }

    @Override
    public String toString() {
        return String.valueOf(this.createdConnections.size());
    }
}
