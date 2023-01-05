package leetcode.concepts.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdjacentListForDirectedGraph {
    private int[][] graph;

    public AdjacentListForDirectedGraph() {
        graph = new int[][] {
                {6, 8}, // node, edge
                {1, 2}, // node1, node2
                {1, 4},
                {2, 4},
                {2, 5},
                {4, 5},
                {4, 6},
                {5, 3},
                {3, 6},
        };
    }

    public Object store() {
        ArrayList<Integer>[] edges = getAdjacentList();
        Object outDegree = getOutDegreeNodes(edges); System.out.println("OutDegree: \n" + outDegree);
        return "InDegree: \n" + getInDegreeNodes(edges);
    }

    private ArrayList<Integer>[] getAdjacentList() {
        int nodeCount = graph[0][0] + 1; // 1 indexed node
        int edgeCount = graph[0][1];
        ArrayList<Integer>[] edges = new ArrayList[nodeCount];
        ArrayList<Integer>[] cost = new ArrayList[nodeCount];

        for (int i = 0; i < nodeCount; i ++) {
            edges[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
//        System.out.println(Stream.of(edges).map(Objects::toString).collect(Collectors.toList()));

        for (int i = 1; i <= edgeCount; i ++ ) {
            int nodeFrom = graph[i][0];
            int nodeTo = graph[i][1];

            edges[nodeFrom].add(nodeTo);
//            edges[nodeTo].add(nodeFrom); // the graph is directed commented out
            cost[nodeFrom].add(1);
//            cost[nodeTo].add(1); // uncomment if bidirectional
        }

        System.out.println("Cost: " + Stream.of(cost) // print cost
                .skip(1)
                .map(
                        l -> l.stream()
                                .map(Object::toString)
                                .collect(Collectors.toList())
                ).collect(Collectors.toList())
        );

        return edges;
    }

    private Object getOutDegreeNodes(ArrayList<Integer>[] edges) {
        AtomicInteger index = new AtomicInteger(1);
        return Stream.of(edges)
                .skip(1) // skip first element; node starts from 1, so skipping 0
                .filter(l -> l.size() != 0) // no node to go from this node
                .map(
                        l -> l.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(",", index.getAndIncrement() + " -> ", ""))
                ).collect(Collectors.joining("\n"));

//        list.forEach(new Consumer<Example>() {
//            int ordinal = 0;
//            public void accept(Example s) {
//                s.setOrdinal(ordinal);
//                ordinal++;
//            }
//        });
    }

    private Object getInDegreeNodes(ArrayList<Integer>[] edges) {
        int nodeCount = graph[0][0] + 1;
        List<List<Integer>> inDegree = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) inDegree.add(new ArrayList<>());

        for (int nodeFrom = 0; nodeFrom < edges.length; nodeFrom ++) {
            for (int index = 0; index < edges[nodeFrom].size(); index ++) {
                int nodeTo = edges[nodeFrom].get(index);
                inDegree.get(nodeTo).add(nodeFrom);
            }
        }

        AtomicInteger node = new AtomicInteger(1);
        return inDegree.stream()
                .skip(1) // skip first node. as no node with
                .map(l -> l.stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining(",", node.getAndIncrement() + " <- ", ""))
                ).collect(Collectors.joining("\n"));
    }

}
