package leetcode.concepts.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Minimum spanning tree by
public class MSTByPrims {
    private int[][] graph; // must be weighted graph for MST
    private ArrayList<Integer>[] edges;
    private ArrayList<Integer>[] cost;

    public MSTByPrims() {
        graph = new int[][]{
                {8, 11}, // node, edge
                {1, 2, 4},
                {1, 4, 10},
                {1, 5, 2},
                {2, 3, 18},
                {2, 4, 8},
                {3, 4, 11},
                {4, 5, 5},
                {4, 7, 11},
                {4, 8, 9},
                {6, 7, 1},
                {6, 8, 2}
        };

        initialiseAdjacentListAndCost();
    }

    public Object getMST() {
        int source = 1;
        return getMSTByPrims(source);
    }

    private int getMSTByPrims(int source) {
        int nodeCount = edges.length + 1;
        boolean[] visited = new boolean[nodeCount];
        visited[source] = true;

        List<MSTCandidate> mst = new ArrayList<>();
        // initialise mst with source
        mst.add(new MSTCandidate(1));

        int mSTCost = 0, mstIndex = 0;
        MSTCandidate previousNode;
        while (!allNodeVisited(mst)) {
            previousNode = mst.get(mstIndex);
            // find minimum cost
            // check if cycle is created
            MSTCandidate nodeToBeAdded = findMinCostWithoutCreatingCycle(mst, visited);
            previousNode.source = nodeToBeAdded.source;
            previousNode.destination = nodeToBeAdded.destination;
            previousNode.cost = nodeToBeAdded.cost;

            // add minimum node to mst
            mst.add(new MSTCandidate(nodeToBeAdded.destination));

            // mark destination node as visited
            visited[nodeToBeAdded.destination] = true;

            // cost += new node cost
            mSTCost += nodeToBeAdded.cost;
            mstIndex ++;
        }
        mst.remove(mst.size() - 1);

        System.out.println("MST: " + mst);
        System.out.println("MST cost: " + mSTCost);
        return mSTCost;
    }

    // use PriorityQue<Integer>[] instead of List<Integer>[] to run mst in O(V * logV)
    private void initialiseAdjacentListAndCost() {
        int nodeCount = graph[0][0] + 1; // 1 indexed node
        int edgeCount = graph[0][1];

        edges = new ArrayList[nodeCount];
        cost = new ArrayList[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            edges[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        // System.out.println(Stream.of(edges).map(Objects::toString).collect(Collectors.toList()));

        for (int i = 1; i <= edgeCount; i++) {
            int nodeFrom = graph[i][0];
            int nodeTo = graph[i][1];
            int weight = graph[i][2];

            edges[nodeFrom].add(nodeTo);
            edges[nodeTo].add(nodeFrom); // the graph is directed commented out
            cost[nodeFrom].add(weight);
            cost[nodeTo].add(weight); // uncomment if bidirectional
        }

        AtomicInteger node = new AtomicInteger(1);
        System.out.println(Stream.of(edges)
                .skip(1)
                .map(l -> l.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", node.getAndIncrement() + " -> ", ""))
                )
                .collect(Collectors.joining("\n"))
        );

        System.out.println("Cost: " + Stream.of(cost) // print cost
                .skip(1)
                .map(
                        l -> l.stream()
                                .map(Object::toString)
                                .collect(Collectors.toList())
                ).collect(Collectors.toList())
        );
    }

    private boolean allNodeVisited(List<MSTCandidate> mappedArray) {
        return mappedArray.size() == edges.length - 1;
    }

    // O(V * E)
    private MSTCandidate findMinCostWithoutCreatingCycle(List<MSTCandidate> mst, boolean[] visited) {
        int sourceNode, lowestCostNode;
        sourceNode = lowestCostNode = edges.length;
        int lowestCost = Integer.MAX_VALUE;

        for (MSTCandidate mstCandidate : mst) {
            int mstNode = mstCandidate.source;
            ArrayList<Integer> sourceNodeEdges = edges[mstNode];
            ArrayList<Integer> sourceNodeCosts = cost[mstNode];
            for (int j = 0; j < sourceNodeEdges.size(); j++) {
                int currentNode = sourceNodeEdges.get(j);
                if (!visited(visited, currentNode)) {
                    int currentCost = sourceNodeCosts.get(j);
//                     System.out.println("node: " + currentNode +",cost: " + currentCost);
                    if (lowestCost > currentCost) {
                        sourceNode = mstNode;
                        lowestCostNode = currentNode;
                        lowestCost = currentCost;
                    }
                }
            }

        }
//        System.out.println("Min node: " + lowestCostNode + ", Min cost: " + lowestCost);
        return new MSTCandidate(sourceNode, lowestCostNode, lowestCost);
    }

    private boolean visited(boolean[] mst, int index) {
        return mst[index];
    }

    private class MSTCandidate {
        public int source;
        public int destination;
        public int cost;

        public MSTCandidate() {}

        public MSTCandidate(int source) { this.source = source; }

        public MSTCandidate(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "{source: " + source + ", destination: " + destination + ", cost: " + cost + "}" ;
        }
    }
}
