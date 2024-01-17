package task3;

import constants.Constants;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Task3 {
    Set<Pair> connectionsSet;
    HashSet<TreeSet<Integer>> createdConnections;

    public Task3(Set<Pair> connectionsSet) {
        this.connectionsSet = connectionsSet;
        createdConnections = new HashSet<>();

        createGraphs();
    }

    public void createGraphs() {
        for (Pair pair : connectionsSet) {
            boolean foundLeft = false, foundRight = false;
            for (TreeSet<Integer> singleSet : createdConnections) {
                if (singleSet.contains(pair.getLeft())) foundLeft = true;
                if (singleSet.contains(pair.getRight())) foundRight = true;
            }
            if (!foundLeft && !foundRight)
                addNewGraph(pair);
            if (foundLeft && !foundRight)
                addToExistingGraph(pair, Constants.LEFT);
            if (!foundLeft && foundRight)
                addToExistingGraph(pair, Constants.RIGHT);
            if (foundLeft && foundRight)
                mergeGraphs(pair);
        }
    }

    private void addNewGraph(Pair pair) {
        createdConnections.add(new TreeSet<>(pair.asList()));
    }

    private void addToExistingGraph(Pair pair, String side) {
        for (TreeSet<Integer> singleSet : createdConnections) {
            if (side.equals(Constants.LEFT) && singleSet.contains(pair.getLeft()))
                singleSet.add(pair.getRight());
            if (side.equals(Constants.RIGHT) && singleSet.contains(pair.getRight()))
                singleSet.add(pair.getLeft());
        }
    }

    private void mergeGraphs(Pair pair) {
        TreeSet<Integer> leftSet = createdConnections
                .stream()
                .filter(singleSet -> singleSet.contains(pair.getLeft()))
                .findFirst()
                .get(); // not checking if exists because it was checked before. I know I could use Optional<>

        TreeSet<Integer> rightSet = createdConnections
                .stream()
                .filter(singleSet -> singleSet.contains(pair.getRight()))
                .findFirst()
                .get();

        rightSet.addAll(leftSet);

        createdConnections.removeIf(singleSet -> singleSet.contains(pair.getLeft()));
        createdConnections.removeIf(singleSet -> singleSet.contains(pair.getRight()));
        createdConnections.add(rightSet);
    }


    public void printResults() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.createdConnections.size());
    }
}
