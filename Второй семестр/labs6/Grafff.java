import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(String vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Вершина не может быть null");
        }
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new LinkedList<>());
            return true;
        }
        return false;
    }

    public void addEdge(String vertex1, String vertex2) {
        Objects.requireNonNull(vertex1, "vertex1 не может быть null");
        Objects.requireNonNull(vertex2, "vertex2 не может быть null");

        addVertex(vertex1);
        addVertex(vertex2);

        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }

    public void addDirectedEdge(String vertex1, String vertex2) {
        Objects.requireNonNull(vertex1, "vertex1 не может быть null");
        Objects.requireNonNull(vertex2, "vertex2 не может быть null");

        addVertex(vertex1);
        addVertex(vertex2);

        adjacencyList.get(vertex1).add(vertex2);
    }

    public Map<String, List<String>> getAdjacencyList() {
        return Collections.unmodifiableMap(adjacencyList);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Добавляю вершины
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        // Добавляю рёбра
        graph.addEdge("A", "B");  // Ненаправленное ребро
        graph.addDirectedEdge("B", "C");  // Направленное ребро (B → C)

        // Попытка добавить ребро с несуществующей вершиной (автоматически создаст D)
        graph.addEdge("C", "D");

        System.out.println("Список смежности:");
        graph.getAdjacencyList().forEach((vertex, neighbors) -> 
            System.out.println(vertex + " -> " + neighbors)
        );
    }
}
