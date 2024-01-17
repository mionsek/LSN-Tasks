package task2;

import java.util.Comparator;

public class Pair implements Comparable<Pair> {
    private final Integer left;
    private final Integer right;

    public Integer getLeft() {
        return this.left;
    }

    public Integer getRight() {
        return this.right;
    }

    public Pair(Integer left, Integer right) {
        if (left > right) {
            this.left = right;
            this.right = left;
        } else {
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public int compareTo(Pair p) {
        return Comparators.PAIR.compare(this, p);
    }

    public static class Comparators {
        public static Comparator<Pair> PAIR = Comparator.comparing(o -> o.left);
    }
}