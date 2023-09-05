package cz.sinkokri.routing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Graph {

    @Builder.Default
    private Map<String, List<String>> adj = new HashMap<>();
    @Builder.Default
    private Map<String, String> parent = new HashMap<>();

    public void addNode(Country country) {
        adj.put(country.getCountryCode(), country.getBorderCountries());
    }

    private boolean exists(String node) {
        return Optional.ofNullable(adj.get(node))
                .isPresent();
    }

    public List<String> bfsSearch(String startNode, String finalNode) {
        if (startNode == null ||
                finalNode == null ||
                !exists(startNode) ||
                !exists(finalNode))
            return Collections.emptyList();

        if (startNode.equals(finalNode)) return List.of(startNode, finalNode);

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.equals(finalNode)) {
                return reconstructPath(startNode, finalNode);
            }

            adj.getOrDefault(current, Collections.emptyList()).stream()
                    .filter(n -> !visited.contains(n))
                    .forEach(n -> {
                                queue.add(n);
                                visited.add(n);
                                parent.put(n, current);
                            }
                    );
        }

        return Collections.emptyList();
    }

    public List<String> reconstructPath(String startNode, String finalNode) {
        List<String> path = new ArrayList<>();
        var currentNode = finalNode;

        while (currentNode != null) {
            path.add(currentNode);
            if (currentNode.equals(startNode)) {
                break;
            }
            currentNode = parent.get(currentNode);
        }

        Collections.reverse(path);
        return path;
    }
}