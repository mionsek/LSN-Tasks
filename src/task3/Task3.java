package task3;

import constants.Constants;

import java.util.*;

public class Task3 {
    Set<Pair> connectionsSet;
    HashSet<TreeSet<Integer>> createdConnections;

    private Map<Integer, Integer> parent;
    private final int numberOfGraphs;

    public Task3(Set<Pair> connectionsSet) {
        this.connectionsSet = connectionsSet;
        createdConnections = new HashSet<>();

//        createGraphs();

        numberOfGraphs = findNumberOfSeparateGraphs(connectionsSet);
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
//        return Math.toIntExact(parent.values().stream().distinct().count());
        int separateGraphs = 0;
        for (var k : parent.keySet())
            if (find(k) == k)
                separateGraphs += 1;
        return separateGraphs;
    }

    public int getNumberOfGraphs() {
        return numberOfGraphs;
    }


//    public void createGraphs() {
//        for (Pair pair : connectionsSet) {
//            boolean foundLeft = false, foundRight = false;
//            for (TreeSet<Integer> singleSet : createdConnections) {
//                if (singleSet.contains(pair.getLeft())) foundLeft = true;
//                if (singleSet.contains(pair.getRight())) foundRight = true;
//            }
//            if (!foundLeft && !foundRight)
//                addNewGraph(pair);
//            else if (foundLeft && !foundRight)
//                addToExistingGraph(pair, Constants.LEFT);
//            else if (!foundLeft && foundRight)
//                addToExistingGraph(pair, Constants.RIGHT);
//            else if (foundLeft && foundRight) // I know its redundant, but looks prettier
//                mergeGraphs(pair);
//        }
//    }

//    private void addNewGraph(Pair pair) {
//        createdConnections.add(new TreeSet<>(pair.asList()));
//    }

//    private void addToExistingGraph(Pair pair, String side) {
//        for (TreeSet<Integer> singleSet : createdConnections) {
//            if (side.equals(Constants.LEFT) && singleSet.contains(pair.getLeft()))
//                singleSet.add(pair.getRight());
//            if (side.equals(Constants.RIGHT) && singleSet.contains(pair.getRight()))
//                singleSet.add(pair.getLeft());
//        }
//    }
//
//    private void mergeGraphs(Pair pair) {
//        // Actually I could skip it there because earlier there was a condition - 'contains'
//        Optional<TreeSet<Integer>> leftSetOptional = getOptional(pair.getLeft());
//        Optional<TreeSet<Integer>> rightSetOptional = getOptional(pair.getRight());
//
//        if (leftSetOptional.isPresent() && rightSetOptional.isPresent()) {
//            TreeSet<Integer> leftSet = leftSetOptional.get();
//            TreeSet<Integer> rightSet = rightSetOptional.get();
//
//            rightSet.addAll(leftSet);
//
//            createdConnections.removeIf(singleSet -> singleSet.contains(pair.getLeft()));
//            createdConnections.removeIf(singleSet -> singleSet.contains(pair.getRight()));
//            createdConnections.add(rightSet);
//
//        }
//    }

//    private Optional<TreeSet<Integer>> getOptional(Integer pair) {
//        return createdConnections
//                .stream()
//                .filter(singleSet -> singleSet.contains(pair))
//                .findFirst();
//    }


    public void printResults() {
        System.out.println(getNumberOfGraphs());
    }

    @Override
    public String toString() {
        return String.valueOf(this.createdConnections.size());
    }
}
