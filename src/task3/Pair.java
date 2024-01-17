package task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pair {
    private final Integer left;
    private final Integer right;

    public Integer getLeft() {
        return this.left;
    }

    public Integer getRight() {
        return this.right;
    }

    public Pair(Integer left, Integer right) {
        this.left = left;
        this.right = right;
    }

    public List<Integer> asList() {
        return new ArrayList<>(Arrays.asList(this.left, this.right));
    }
}