package com.example._datastruc2_20102669;

import java.security.Key;
import java.util.*;

public class GraphAPI {


    public static <T> List<List<GraphInfo<?>>> depthFirstAllPaths(GraphInfo<?> from, List<GraphInfo<?>> encountered, T lookingfor) {
        List<List<GraphInfo<?>>> result = new ArrayList<>();

        // Check if the current node contains the data we're looking for
        if (from.nodeData.equals(lookingfor)) {
            List<GraphInfo<?>> path = new ArrayList<>();
            path.add(from);
            result.add(path);
            return result;
        }
        // Initialize the encountered list if it’s null and add the current node
        if (encountered == null) {
            encountered = new ArrayList<>();
        }
        encountered.add(from);

        for (LinkedGraph adjNode : from.neighbour) {        // Explores each adjacent node
            if (!encountered.contains(adjNode.destinNode)) {
                List<List<GraphInfo<?>>> paths = depthFirstAllPaths(adjNode.destinNode, new ArrayList<>(encountered), lookingfor);                // Recursively find paths from the adjacent node

                // Ensure paths is not null and process each path
                if (paths != null) {
                    for (List<GraphInfo<?>> path : paths) {
                        path.add(0, from); // Prepend the current node to the found path
                        result.add(path);
                    }
                }
            }
        }

        return result.isEmpty() ? null : result;
    }

    public static <T> List<GraphInfo<?>> depthFirstPath(GraphInfo<?> from, List<GraphInfo<?>> encountered, T lookingfor) {
        if (from.nodeData.equals(lookingfor)) {
            return Collections.singletonList(from);
        }

        // Initialize the encountered list if it's null and add the current node to it
        if (encountered == null) {
            encountered = new ArrayList<>();
        }
        encountered.add(from);

        for (LinkedGraph adjNode : from.neighbour) {
            if (!encountered.contains(adjNode.destinNode)) {
                // Recursively search for the target data
                List<GraphInfo<?>> path = depthFirstPath(adjNode.destinNode, encountered, lookingfor);
                if (path != null) {
                    // Prepend the current node to the path and return it
                    path.add(0, from);
                    return path;
                }
            }
        }

        return null;
    }

    public static int calEdgeWeight(GraphInfo<LandmarkInfo> node1, GraphInfo<LandmarkInfo> node2) {
        int x1 = node1.nodeData.getxCoord();
        int y1 = node1.nodeData.getyCoord();
        int x2 = node2.nodeData.getxCoord();
        int y2 = node2.nodeData.getyCoord();

        // Calculate squared differences
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Calculate the Euclidean distance and cast to int
        return (int) Math.hypot(dx, dy);
    }


    public static int calTotPathDistance(List<GraphInfo<?>> path) {
        int totalCost = 0;

        // Loop through the path, calculating distances between consecutive nodes
        for (int i = 0; i < path.size() - 1; i++) {
            GraphInfo<?> currentNode = path.get(i);
            GraphInfo<?> nextNode = path.get(i + 1);

            // Ensure the next node is not null before calculating the distance
            if (nextNode != null) {
                totalCost += calEdgeWeight((GraphInfo<LandmarkInfo>) currentNode, (GraphInfo<LandmarkInfo>) nextNode);
            }
        }

        return totalCost;
    }

    public static List<GraphInfo<LandmarkInfo>> dijkstraLightestPath(
            GraphInfo<LandmarkInfo> startNode, GraphInfo<LandmarkInfo> endNode,
            Collection<GraphInfo<LandmarkInfo>> allNodes) {

        final int INFINITY = Integer.MAX_VALUE;

        // Maps shortest distances and the previous nodes
        Map<GraphInfo<LandmarkInfo>, Integer> distances = new HashMap<>();
        Map<GraphInfo<LandmarkInfo>, GraphInfo<LandmarkInfo>> previous = new HashMap<>();

        // Priority queue to process nodes by smallest distance
        PriorityQueue<GraphInfo<LandmarkInfo>> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize distances and add nodes to the queue
        for (GraphInfo<LandmarkInfo> node : allNodes) {
            distances.put(node, INFINITY);
            previous.put(node, null);
        }
        distances.put(startNode, 0);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            GraphInfo<LandmarkInfo> current = queue.poll();

            if (current.equals(endNode)) {
                // Reconstruct the path
                List<GraphInfo<LandmarkInfo>> path = new LinkedList<>();
                for (GraphInfo<LandmarkInfo> node = endNode; node != null; node = previous.get(node)) {
                    path.add(node);
                }
                Collections.reverse(path); // Reverse to get the correct order
                return path;
            }

            // Update distances for each neighbor
            for (LinkedGraph<LandmarkInfo> edge : current.neighbour) {
                GraphInfo<LandmarkInfo> neighbor = edge.destinNode;
                int newDist = distances.get(current) + edge.weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    if (!queue.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return null; // Return null if no path is found
    }


    public static List<GraphInfo<LandmarkInfo>> dijkstraHistoricPath(
            GraphInfo<LandmarkInfo> startNode,
            GraphInfo<LandmarkInfo> destNode,
            List<GraphInfo<LandmarkInfo>> landmarkNodes) {

        // Map to hold shortest distances from startNode to each node
        Map<GraphInfo<LandmarkInfo>, Integer> distances = new HashMap<>();
        // Map to track the previous node in the shortest path
        Map<GraphInfo<LandmarkInfo>, GraphInfo<LandmarkInfo>> previousNodes = new HashMap<>();

        // Priority queue to hold nodes to be explored, ordered by shortest distance
        PriorityQueue<GraphInfo<LandmarkInfo>> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initializing distances and priority queue
        for (GraphInfo<LandmarkInfo> node : landmarkNodes) {
            distances.put(node, Integer.MAX_VALUE);
            previousNodes.put(node, null);
        }
        distances.put(startNode, 0);
        pq.add(startNode);

        while (!pq.isEmpty()) {
            GraphInfo<LandmarkInfo> current = pq.poll();
            int currentDistance = distances.get(current);

            if (current.equals(destNode)) {
                break;
            }

            // Check neighbors and update distances
            for (GraphInfo<LandmarkInfo> neighbor : landmarkNodes) {
                if (!neighbor.equals(current)) {
                    int edgeWeight = GraphAPI.calEdgeWeight(current, neighbor);
                    int newDist = currentDistance + edgeWeight;
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previousNodes.put(neighbor, current);
                        pq.add(neighbor);
                    }
                }
            }
        }

        // Reconstruct the path from destNode to startNode
        List<GraphInfo<LandmarkInfo>> path = new LinkedList<>();
        for (GraphInfo<LandmarkInfo> at = destNode; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return path;
    }


    public static List<GraphInfo<LandmarkInfo>> shortBFSPath(GraphInfo<LandmarkInfo> startNode, GraphInfo<LandmarkInfo> endNode) {
        List<GraphInfo<LandmarkInfo>> path = new ArrayList<>();

        if (startNode == null || endNode == null) return path; // Handle null input

        // Use a Queue to explore nodes in BFS order
        Queue<GraphInfo<LandmarkInfo>> queue = new LinkedList<>();
        queue.add(startNode);

        // Map to keep track of the path from each node
        Map<GraphInfo<LandmarkInfo>, GraphInfo<LandmarkInfo>> predecessors = new HashMap<>();
        predecessors.put(startNode, null);

        // Set to keep track of visited nodes
        Set<GraphInfo<LandmarkInfo>> visitedNodes = new HashSet<>();
        visitedNodes.add(startNode);

        while (!queue.isEmpty()) {
            GraphInfo<LandmarkInfo> currentNode = queue.poll();

            if (currentNode.equals(endNode)) {
                return pathBuild(predecessors, endNode);
            }

            for (LinkedGraph edge : currentNode.neighbour) {
                GraphInfo<LandmarkInfo> neighbor = edge.destinNode;

                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    predecessors.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }
        }

        return path; // Return an empty list if no path is found
    }

    private static List<GraphInfo<LandmarkInfo>> pathBuild(Map<GraphInfo<LandmarkInfo>, GraphInfo<LandmarkInfo>> predecessors, GraphInfo<LandmarkInfo> endNode) {
        List<GraphInfo<LandmarkInfo>> path = new ArrayList<>();
        for (GraphInfo<LandmarkInfo> node = endNode; node != null; node = predecessors.get(node)) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;

    }
}
