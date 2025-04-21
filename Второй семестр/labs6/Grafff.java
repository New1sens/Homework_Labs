package labs6;
import java.util.*;

class Graph {
    private final Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Вершина не может быть null");
        }
        adjacencyList.putIfAbsent(vertex, new LinkedList<>());
    }

    public void addEdge(String vertex1, String vertex2) {
        checkVertexExists(vertex1);
        checkVertexExists(vertex2);

        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }

    public void addDirectedEdge(String vertex1, String vertex2) {
        checkVertexExists(vertex1);
        checkVertexExists(vertex2);

        adjacencyList.get(vertex1).add(vertex2);
    }

    private void checkVertexExists(String vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Вершина не может быть null");
        }
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Вершина " + vertex + " не существует");
        }
    }

    public Map<String, List<String>> getAdjacencyList() {
        return Collections.unmodifiableMap(adjacencyList);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Добавляем вершины
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        // добавление ребер
        graph.addEdge("A", "B");  // A <-> B
        graph.addDirectedEdge("B", "C");  // B -> C

        // Попытка добавить ребро с несуществующей вершиной (выбросит исключение)
        try {
            graph.addEdge("C", "D");  // Ошибка: вершина D не существует
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        System.out.println("Список смежности:");
        graph.getAdjacencyList().forEach((vertex, neighbors) ->
                System.out.println(vertex + " -> " + neighbors)
        );
    }
}
