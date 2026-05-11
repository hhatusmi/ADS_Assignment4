import java.util.*;
public class GraphTraversal {
    private Map<String, List<String>> adjacencyList = new HashMap<>();
    public GraphTraversal() {
        adjacencyList.put("A", new ArrayList<>(List.of("C", "B", "D")));
        adjacencyList.put("B", new ArrayList<>(List.of("A", "C", "E", "G")));
        adjacencyList.put("C", new ArrayList<>(List.of("A", "B", "D")));
        adjacencyList.put("D", new ArrayList<>(List.of("C", "A")));
        adjacencyList.put("E", new ArrayList<>(List.of("G", "F", "B")));
        adjacencyList.put("F", new ArrayList<>(List.of("G", "E")));
        adjacencyList.put("G", new ArrayList<>(List.of("F", "B")));
    }
    public void depthFirstSearch(String startVertex) {
        Set<String> visitedVertices = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsRecursive(startVertex, visitedVertices);
        System.out.println();
    }
    private void dfsRecursive(String currentVertex, Set<String> visitedVertices) {
        visitedVertices.add(currentVertex);
        System.out.print(currentVertex + " ");
        for (String neighborVertex : adjacencyList.get(currentVertex)) {
            if (!visitedVertices.contains(neighborVertex)) {
                dfsRecursive(neighborVertex, visitedVertices);
            }
        }
    }
    public void breadthFirstSearch(String startVertex) {
        Set<String> visitedVertices = new HashSet<>();
        Queue<String> vertexQueue = new LinkedList<>();
        visitedVertices.add(startVertex);
        vertexQueue.offer(startVertex);
        System.out.print("BFS Traversal: ");
        while (!vertexQueue.isEmpty()) {
            String currentVertex = vertexQueue.poll();
            System.out.print(currentVertex + " ");
            for (String neighborVertex : adjacencyList.get(currentVertex)) {
                if (!visitedVertices.contains(neighborVertex)) {
                    visitedVertices.add(neighborVertex);
                    vertexQueue.offer(neighborVertex);
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        GraphTraversal graphTraversal = new GraphTraversal();
        graphTraversal.depthFirstSearch("A");
        graphTraversal.breadthFirstSearch("A");
    }
}
