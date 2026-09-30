package ru.kt4.graph;

import java.util.Map;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        TaskGraph graph = new TaskGraph();

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("C", "B", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 8);
        graph.addEdge("D", "E", 3);
        graph.addTask("F");

        System.out.println("DFS от A: " + Algorithms.dfs(graph, "A"));
        System.out.println("BFS от A: " + Algorithms.bfs(graph, "A"));
        System.out.println("Групп задач: " + Algorithms.countGroups(graph));

        System.out.println("Есть цикл? " + Algorithms.hasCycle(graph));

        graph.addEdge("E", "A", 1);
        System.out.println("После E->A есть цикл? " + Algorithms.hasCycle(graph));

        TaskGraph secondGraph = new TaskGraph();
        secondGraph.addEdge("A", "B", 4);
        secondGraph.addEdge("A", "C", 2);
        secondGraph.addEdge("C", "B", 1);
        secondGraph.addEdge("B", "D", 5);
        secondGraph.addEdge("C", "D", 8);
        secondGraph.addEdge("D", "E", 3);
        secondGraph.addTask("F");

        Map<String, Integer> distance = Algorithms.dijkstra(secondGraph, "A");
        System.out.println("От A:");
        for (String task : new TreeSet<>(distance.keySet())) {
            int value = distance.get(task);
            if (value == Integer.MAX_VALUE) {
                System.out.println("  " + task + " = недостижимо");
            } else {
                System.out.println("  " + task + " = " + value);
            }
        }
    }
}