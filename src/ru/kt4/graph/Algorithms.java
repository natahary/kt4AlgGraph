package ru.kt4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Algorithms {

    static List<String> dfs(TaskGraph graph, String start) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsHelper(graph, start, visited, result);
        return result;
    }

    static void dfsHelper(TaskGraph graph, String node, Set<String> visited, List<String> result) {

        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        result.add(node);

        for (String next : graph.getNeighbors(node).keySet()) {
            dfsHelper(graph, next, visited, result);
        }

    }

    static List<String> bfs(TaskGraph graph, String start) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            result.add(node);

            for (String next : graph.getNeighbors(node).keySet()) {

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }

            }

        }
        return result;
    }

    static int countGroups(TaskGraph graph) {
        Set<String> visited = new HashSet<>();
        int groups = 0;

        for (String task : graph.getAllTasks()) {

            if (!visited.contains(task)) {
                groups++;
                Queue<String> queue = new LinkedList<>();
                queue.add(task);
                visited.add(task);

                while (!queue.isEmpty()) {
                    String node = queue.poll();

                    for (String next : graph.getNeighbors(node).keySet()) {

                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.add(next);
                        }

                    }

                }

            }

        }
        return groups;
    }

    static boolean hasCycle(TaskGraph graph) {
        Map<String, String> color = new HashMap<>();

        for (String task : graph.getAllTasks()) {
            color.put(task, "WHITE");
        }

        for (String task : graph.getAllTasks()) {

            if (color.get(task).equals("WHITE")) {

                if (cycleHelper(graph, task, color)) {
                    return true;
                }

            }

        }
        return false;
    }

    static boolean cycleHelper(TaskGraph graph, String node, Map<String, String> color) {
        color.put(node, "GRAY");

        for (String next : graph.getNeighbors(node).keySet()) {
            String nextColor = color.get(next);

            if (nextColor.equals("GRAY")) {
                return true;
            }

            if (nextColor.equals("WHITE") && cycleHelper(graph, next, color)) {
                return true;
            }

        }

        color.put(node, "BLACK");
        return false;
    }

    static Map<String, Integer> dijkstra(TaskGraph graph, String start) {
        Map<String, Integer> distance = new HashMap<>();

        for (String task : graph.getAllTasks()) {
            distance.put(task, Integer.MAX_VALUE);
        }


        distance.put(start, 0);

        PriorityQueue<String> queue = new PriorityQueue<>((first, second) -> {
            int firstDistance = distance.get(first);
            int secondDistance = distance.get(second);
            return Integer.compare(firstDistance, secondDistance);
        });

        queue.add(start);

        Set<String> done = new HashSet<>();

        while (!queue.isEmpty()) {
            String node = queue.poll();

            if (done.contains(node)) {
                continue;
            }

            done.add(node);

            for (Map.Entry<String, Integer> edge : graph.getNeighbors(node).entrySet()) {
                String next = edge.getKey();
                int weight = edge.getValue();
                int newDistance = distance.get(node) + weight;

                if (newDistance < distance.get(next)) {
                    distance.put(next, newDistance);
                    queue.add(next);
                }
            }
        }
        return distance;
    }
}