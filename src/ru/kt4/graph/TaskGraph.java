package ru.kt4.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaskGraph {

    Map<String, Map<String, Integer>> graph = new HashMap<>();

    void addTask(String task) {

        if (!graph.containsKey(task)) {
            graph.put(task, new HashMap<>());
        }

    }

    void addEdge(String from, String to, int weight) {

        if (weight < 0) {
            System.out.println("Ошибка: вес не может быть отрицательным");
            return;
        }

        addTask(from);
        addTask(to);
        graph.get(from).put(to, weight);
    }

    Map<String, Integer> getNeighbors(String task) {

        if (!graph.containsKey(task)) {
            return new HashMap<>();
        }

        return graph.get(task);
    }

    Set<String> getAllTasks() {
        return graph.keySet();
    }

    boolean hasTask(String task) {
        return graph.containsKey(task);
    }
}