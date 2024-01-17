package task1;

import java.util.*;

public class Task1 {
    private final List<Integer> integerList;
    private final List<Integer> distinctList;

    public Task1(List<Integer> integerList) {
        this.integerList = integerList;
        this.distinctList = new ArrayList<>(new HashSet<>(integerList));
        sortDistinctList();
    }

    public int getCount() {
        return this.integerList.size();
    }

    public int getDistinct() {
        return this.distinctList.size();
    }

    public int getMax() {
        return Collections.max(this.distinctList);
    }

    private void sortDistinctList() {
        Collections.sort(distinctList);
    }

    public int getMin() {
        return Collections.min(this.distinctList);
    }

    private String listToString() {
        return Arrays.toString(this.distinctList.toArray())
                .replace("[", "")
                .replace("]", "")
                .replaceAll(",", "");
    }

    public void printResults() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return listToString() + "\n" +
                "count: " + getCount() + "\n" +
                "distinct: " + getDistinct() + "\n" +
                "min: " + getMin() + "\n" +
                "max: " + getMax();
    }
}
