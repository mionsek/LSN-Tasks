package task2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2 {
    private final List<Integer> integerList;
    private final List<Pair> pairList = new ArrayList<>();

    public Task2(List<Integer> integerList) {
        long startTime = System.currentTimeMillis();
        this.integerList = integerList;
        findAllPairs();
        long endTime = System.currentTimeMillis();
//        System.out.println("\n\nDuration: " + (endTime - startTime) + " ms");
    }

    private void findAllPairs() {
        LinkedHashMap<Integer, Long> counts = getIntegerCounts();
        counts.keySet().forEach(key -> checkForPairAndAddIfExists(counts, key));
        pairList.sort(Pair.Comparators.PAIR);
    }

    public void printResults() {
        pairList.forEach(pair -> System.out.println(pair.getLeft() + " - " + pair.getRight()));
    }

    private void checkForPairAndAddIfExists(LinkedHashMap<Integer, Long> counts, Integer key) {
        int wantedValueKey = 13 - key;
        if (counts.containsKey(wantedValueKey) && counts.get(key) > 0 && counts.get(wantedValueKey) > 0) {
            addPairToSet(key, wantedValueKey);
            decreaseCounts(counts, key, wantedValueKey);
        }
    }

    private void decreaseCounts(LinkedHashMap<Integer, Long> counts, Integer key, int wantedValueKey) {
        counts.replace(key, counts.get(key) - 1);
        counts.replace(wantedValueKey, counts.get(wantedValueKey) - 1);
    }

    private void addPairToSet(Integer key, int wantedValueKey) {
        this.pairList.add(new Pair(wantedValueKey, key));
    }

    private LinkedHashMap<Integer, Long> getIntegerCounts() {
        Map<Integer, Long> counts =
                this.integerList.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return new LinkedHashMap<>(counts);
    }
}
