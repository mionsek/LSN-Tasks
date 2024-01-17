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
            else if (foundLeft && !foundRight)
                addToExistingGraph(pair, Constants.LEFT);
            else if (!foundLeft && foundRight)
                addToExistingGraph(pair, Constants.RIGHT);
            else if (foundLeft && foundRight) // i know its redundant, but looks prettier
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
        // Actually I could skip it there because earlier there was a condition - 'contains'
        Optional<TreeSet<Integer>> leftSetOptional = getOptional(pair.getLeft());
        Optional<TreeSet<Integer>> rightSetOptional = getOptional(pair.getRight());

        if (leftSetOptional.isPresent() && rightSetOptional.isPresent()){
            TreeSet<Integer> leftSet = leftSetOptional.get();
            TreeSet<Integer> rightSet = rightSetOptional.get();

            rightSet.addAll(leftSet);

            createdConnections.removeIf(singleSet -> singleSet.contains(pair.getLeft()));
            createdConnections.removeIf(singleSet -> singleSet.contains(pair.getRight()));
            createdConnections.add(rightSet);

        }
    }

    private Optional<TreeSet<Integer>> getOptional(Integer pair) {
        return createdConnections
                .stream()
                .filter(singleSet -> singleSet.contains(pair))
                .findFirst();
    }


    public void printResults() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.createdConnections.size());
    }
}
