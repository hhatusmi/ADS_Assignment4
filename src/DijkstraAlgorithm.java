import java.util.*;
class Edge {
    String destinationCity;
    int distance;
    public Edge(String destinationCity, int distance) {
        this.destinationCity = destinationCity;
        this.distance = distance;
    }
}
public class DijkstraAlgorithm {
    private Map<String, List<Edge>> roadNetwork = new HashMap<>();
    public void addRoad(String sourceCity, String destinationCity, int distance) {
        roadNetwork.putIfAbsent(sourceCity, new ArrayList<>());
        roadNetwork.putIfAbsent(destinationCity, new ArrayList<>());
        roadNetwork.get(sourceCity).add(new Edge(destinationCity, distance));
        roadNetwork.get(destinationCity).add(new Edge(sourceCity, distance));
    }
    public void shortestPath(String startCity) {
        Map<String, Integer> minimumDistances = new HashMap<>();
        for (String city : roadNetwork.keySet()) {
            minimumDistances.put(city, Integer.MAX_VALUE);
        }
        minimumDistances.put(startCity, 0);
        PriorityQueue<String> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(minimumDistances::get));
        priorityQueue.offer(startCity);
        while (!priorityQueue.isEmpty()) {
            String currentCity = priorityQueue.poll();
            for (Edge road : roadNetwork.get(currentCity)) {
                int newDistance =
                        minimumDistances.get(currentCity) + road.distance;
                if (newDistance < minimumDistances.get(road.destinationCity)) {
                    minimumDistances.put(road.destinationCity, newDistance);
                    priorityQueue.offer(road.destinationCity);
                }
            }
        }
        System.out.println("Shortest distances from " + startCity + ":");
        for (String city : minimumDistances.keySet()) {
            System.out.println(city + " : " + minimumDistances.get(city));
        }
    }
    public static void main(String[] args) {
        DijkstraAlgorithm graph = new DijkstraAlgorithm();
        graph.addRoad("Edinburgh", "Perth", 40);
        graph.addRoad("Perth", "Dundee", 22);
        graph.addRoad("Edinburgh", "Dundee", 65);
        graph.shortestPath("Edinburgh");
    }
}