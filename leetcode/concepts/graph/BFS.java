package leetcode.concepts.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// breadth first search
public class BFS {
    private int[][] graph;
    public BFS() {
        graph = new int[][] {
                {10, 13}, // node, edge
                {1, 2}, // node1, node2
                {1, 3},
                {1, 4},
                {2, 6},
                {3, 7},
                {3, 8},
                {4, 7},
                {5, 8},
                {5, 10},
                {6, 10},
                {7, 8},
                {7, 9},
                {9, 10}
        };
    }

    // breadth first search
    public Object findDistanceByBFS() {
        List<List<Integer>> edges = getAdjacentList();
        int from = 1, to = 10;
        return bFSByIteration(edges, from, to);
    }

    private List<List<Integer>> getAdjacentList() {
        int nodeCount = graph[0][0] + 1;
        int edgeCount = graph[0][1];

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < nodeCount; i ++) edges.add(new ArrayList<>());

        for (int i = 1; i <= edgeCount; i++) {
            int nodeFrom = graph[i][0];
            int nodeTo = graph[i][1];

            edges.get(nodeFrom).add(nodeTo);
            edges.get(nodeTo).add(nodeFrom);
        }

        AtomicInteger node = new AtomicInteger(1);
        System.out.println(edges.stream()
                .skip(1)
                .map(l -> l.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ",  node.getAndIncrement() + " -> ", ""))
                )
                .collect(Collectors.joining("\n"))
        );
        return edges;
    }

    private <T> Integer bFSByIteration(List<List<T>> edges, T source, T destination) {
        Map<T, Boolean> visited = new HashMap<>(); /* boolean[] can be used instead of map */
        Map<T, Integer> level = new HashMap<>(); /* int[] can be used instead of map */
        Queue<T> queue = new PriorityQueue<>();


        queue.add(source);
        level.put(source, 0); // level of source is 0

        while (!queue.isEmpty()) {
            T top = queue.remove();
            visited.put(top, true);

            int t = (int)top;
            List<T> adjacentNodes = edges.get(t);
            List<T> adjacentNodesThatNotVisited = adjacentNodes.stream() // can be optimized by boolean array
                    .filter(node -> !visited.containsKey(node))
                    .collect(Collectors.toList());
            queue.addAll(adjacentNodesThatNotVisited);

            AtomicBoolean found = new AtomicBoolean(false);
            Integer parentLevel = level.get(top);
            Integer currentLevel = parentLevel + 1;
            adjacentNodesThatNotVisited.forEach(node -> { // can be optimized by boolean[]
                visited.put(node, true);
                level.put(node, currentLevel);
                if (node == destination)
                    found.set(true);
            });

            if (found.get())
                return currentLevel;

            System.out.println(queue);
        }

        return -1; // not found
    }
}
