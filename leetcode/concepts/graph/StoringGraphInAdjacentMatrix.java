package leetcode.concepts.graph;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoringGraphInAdjacentMatrix {
    public int[][] store() {
        int[][] graph = new int[][]{
                {4, 3}, // Node, Edge
                {0, 1, 5}, // node, node, cost
                {1, 3, 9},
                {1, 2, 8},
                {0, 2, 3},
                {2, 3, 5}
        };

        // expected [[0, 5, 3, 0], [5, 0, 8, 9], [3, 8, 0, 5], [0, 9, 5, 0]]
        return getAdjacentMatrix(graph);
    }

    private int[][] getAdjacentMatrix(int[][] graphInput) {
        int nodeCount = graphInput[0][0];
        int[][] outputMatrix = new int[nodeCount][nodeCount];

        for (int i = 1; i < graphInput.length; i++) {
            int nodeFrom = graphInput[i][0];
            int nodeTo = graphInput[i][1];
            int cost = graphInput[i][2];

            outputMatrix[nodeFrom][nodeTo] = cost;
            outputMatrix[nodeTo][nodeFrom] = cost;
        }

        System.out.println(Stream.of(outputMatrix).map(Arrays::toString).collect(Collectors.toList()));

        return outputMatrix;
    }
}
