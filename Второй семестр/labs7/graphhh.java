package labs7;

import java.util.*;

class DirectedGraph {
    private final int maxVertices;
    private final Vertex[] vertexList;
    private final int[][] adjacencyMatrix;
    private int vertexCount;

    public DirectedGraph(int maxVertices) {
        this.maxVertices = maxVertices;
        this.vertexList = new Vertex[maxVertices];
        this.adjacencyMatrix = new int[maxVertices][maxVertices];
        this.vertexCount = 0;
        for (int i = 0; i < maxVertices; i++) {
            Arrays.fill(adjacencyMatrix[i], 0);
        }
    }

    public void addVertex(char label) {
        if (vertexCount < maxVertices) {
            vertexList[vertexCount++] = new Vertex(label);
        }
    }

    public void addEdge(int start, int end) {
        if (start >= 0 && start < vertexCount && end >= 0 && end < vertexCount) {
            adjacencyMatrix[start][end] = 1;
        }
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].getLabel());
    }

    // Топологическая сортировка без удаления вершин из графа
    public void topologicalSort() {
        int[] inDegree = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjacencyMatrix[j][i] == 1) {
                    inDegree[i]++;
                }
            }
        }
        // Очередь для вершин с нулевым входящим степенью
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int visitedVertices = 0;
        List<Character> topOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            topOrder.add(vertexList[currentVertex].getLabel());
            visitedVertices++;

            for (int neighbor = 0; neighbor < vertexCount; neighbor++) {
                if (adjacencyMatrix[currentVertex][neighbor] == 1) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Проверка на циклы
        if (visitedVertices != vertexCount) {
            System.out.println("Ошибка граф зацикленный");
            return;
        }

        // Вывод топологически отсортированного порядка
        System.out.print("Топологически отсортированный порядок:");
        for (char vertex : topOrder) {
            System.out.print(vertex);
        }
        System.out.println();
    }

    // Вспомогательный класс для хранения вершин
    private static class Vertex {
        private final char label;

        public Vertex(char label) {
            this.label = label;
        }

        public char getLabel() {
            return label;
        }
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(8);

        // Добавляем вершины
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addVertex('F'); // 5
        graph.addVertex('G'); // 6
        graph.addVertex('H'); // 7

        // Добавляю ребра
        graph.addEdge(0, 1); // A -> B
        graph.addEdge(0, 2); // A -> C
        graph.addEdge(1, 3); // B -> D
        graph.addEdge(1, 4); // B -> E
        graph.addEdge(2, 4); // C -> E
        graph.addEdge(3, 5); // D -> F
        graph.addEdge(4, 5); // E -> F
        graph.addEdge(4, 6); // E -> G
        graph.addEdge(5, 7); // F -> H
        graph.addEdge(6, 7); // G -> H

        // Выполняется топологическую сортировку
        graph.topologicalSort();
    }
}
