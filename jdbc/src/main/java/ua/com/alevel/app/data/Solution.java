package ua.com.alevel.app.data;

public class Solution {

    private int problemId;
    private int cost;

    public Solution(int problemId, int cost) {
        this.problemId = problemId;
        this.cost = cost;
    }

    public int getProblemId() {
        return problemId;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "problemId=" + problemId +
                ", cost=" + cost +
                '}';
    }
}
