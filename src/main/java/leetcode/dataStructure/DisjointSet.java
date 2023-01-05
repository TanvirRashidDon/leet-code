package leetcode.dataStructure;

import java.util.Arrays;

public class DisjointSet {
    private int[][] graph;
    public DisjointSet() {
        graph = new int[][] {
                {7, 5}, // node, edge
                {1, 2}, // node1, node2
                {1, 3},
                {1, 4},
                {5, 6},
                {6, 7}
        };
    }

    public Object getDisjointSet() {
        int[] disjointSet = initialiseDisjointSet();
        System.out.println(Arrays.toString(disjointSet));

        return disjointSet;
    }

    private int[] initialiseDisjointSet() {
        int nodeCount = graph[0][0] + 1; // 1 indexed
        int edgeCount = graph[0][1];

        int[] disjointDS = new int[nodeCount];
        for (int i = 1; i < nodeCount; i++) disjointDS[i] = i;

        for (int i = 1; i <= edgeCount; i++) {
            int sourceNode = graph[i][0];
            int destinationNode = graph[i][1];

            setRepresentative(sourceNode, destinationNode, disjointDS);
        }

        return disjointDS;
    }

    private void setRepresentative(int sourceNode, int destinationNode, int[] disjointDS) {
        int sourceRepresentative = findRepresentative(sourceNode, disjointDS);
        int destinationRepresentative = findRepresentative(destinationNode, disjointDS);

        disjointDS[destinationRepresentative] = sourceRepresentative;
    }

    private int findRepresentative(int sourceNode, int[] disjointDS) {
        if (sourceNode == disjointDS[sourceNode])
            return sourceNode;

        return findRepresentative(disjointDS[sourceNode], disjointDS);
    }
}
