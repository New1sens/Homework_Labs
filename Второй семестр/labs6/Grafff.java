package labs6;

import java.util.*;

class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new LinkedList<>());
        }
    }
    //Добавляет ненаправленное ребро между двумя вершинами

    public void addEdge(String vertex1, String vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);

        // Добавляем рёбра в обе стороны (ненаправленный граф)
        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }


     // Добавляет направленное ребро от vertex1 к vertex2
    public void addDirectedEdge(String vertex1, String vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);
        adjacencyList.get(vertex1).add(vertex2);
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Добавляю вершины
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Добавляю рёбра
        graph.addEdge("A", "B"); // ненаправленное ребро
        graph.addDirectedEdge("B", "C"); //направленное ребро
        graph.addDirectedEdge("B", "D"); //направленное ребро

        // Вывод смежности
        System.out.println(graph.getAdjacencyList());
    }
}
