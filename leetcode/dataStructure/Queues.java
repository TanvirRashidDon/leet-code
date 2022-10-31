package leetcode.dataStructure;

import java.util.*;

public class Queues {
    private Queue<Integer> queue;

    public Queues() {
        queue = new PriorityQueue<>();
    }

    public Object queueExample() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        queue.addAll(list);
        System.out.println(queue);

        queue.remove();
        System.out.println(queue);

        return queue;
    }
}
