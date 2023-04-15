import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllPathsBetweenNodes {
    public static List<List<Integer>> findPaths(Map<Integer, List<Integer>> graph, int source, int target) {
        Set<Integer> visited = new HashSet<>(); // To keep track of visited nodes
        List<List<Integer>> paths = new ArrayList<>(); // To store all found paths

        dfs(graph, source, target, visited, new ArrayList<>(), paths);

        return paths;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int node, int target, Set<Integer> visited,
            List<Integer> path, List<List<Integer>> paths) {
        // Depth-first search (DFS) function to explore all paths from source to target
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        path.add(node); // Append the current node to the current path

        if (node == target) { // If target node is reached, add the current path to the list of paths
            paths.add(new ArrayList<>(path));
        } else { // Continue exploring neighboring nodes
            List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
            for (int neighbor : neighbors) {
                dfs(graph, neighbor, target, visited, path, paths);
            }
        }

        visited.remove(node); // Remove the node from visited set for backtracking
        path.remove(path.size() - 1); // Remove the node from path for backtracking
    }

    public static void main(String[] args) {
        // Example usage
        Map<Integer, List<Integer>> graph = new HashMap<>();
        /*graph.put(1, List.of(2, 3)); // Edge from 1 to 2, 1 to 3
        graph.put(2, List.of(3, 4)); // Edge from 2 to 3, 2 to 4
        graph.put(3, List.of(4)); // Edge from 3 to 4
*/
        int source = 1;
        int target = 4;
        List<List<Integer>> paths = findPaths(graph, source, target);
        System.out.println("All paths from " + source + " to " + target + ": ");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}
