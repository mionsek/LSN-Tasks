package task2;

import constants.Constants;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2 {
    private final List<Integer> integerList;
    private final List<Pair> pairList = new ArrayList<>();

    public Task2(List<Integer> integerList) {
//        long startTime = System.currentTimeMillis();
        this.integerList = integerList;
        findAllPairs();
//        long endTime = System.currentTimeMillis();
//        System.out.println("\n\nDuration: " + (endTime - startTime) + " ms");
    }

    private void findAllPairs() {
        LinkedHashMap<Integer, Long> counts = getIntegerCounts();
        counts.keySet().forEach(key -> checkForPairAndAddIfExists(counts, key));
        pairList.sort(Pair.Comparators.PAIR);
    }

    private LinkedHashMap<Integer, Long> getIntegerCounts() {
        Map<Integer, Long> counts =
                this.integerList.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return new LinkedHashMap<>(counts);
    }

    private void checkForPairAndAddIfExists(LinkedHashMap<Integer, Long> counts, Integer key) {
        int wantedValueKey = Constants.SUM - key;
        if (counts.containsKey(wantedValueKey) && counts.get(key) > 0 && counts.get(wantedValueKey) > 0) {
            addPairsToSet(counts, key, wantedValueKey);
            decreaseCounts(counts, key, wantedValueKey);
        }
    }

    private void addPairsToSet(LinkedHashMap<Integer, Long> counts, int key, int wantedValueKey) {
        var combined = counts.get(key) * counts.get(wantedValueKey);
        for (var cnt = 0; cnt < combined; cnt += 1)
            this.pairList.add(new Pair(wantedValueKey, key));
    }


    private void decreaseCounts(LinkedHashMap<Integer, Long> counts, Integer key, int wantedValueKey) {
        counts.replace(key, 0L);
        counts.replace(wantedValueKey, 0L);
    }

    public void printResults() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        pairList.forEach(pair ->
                sb.append(pair.getLeft())
                        .append(" ")
                        .append(pair.getRight())
                        .append("\n"));
        return sb.toString();
    }
}
