import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Dijkstra {

    private String resultPath = "A";
    private int count = 0;
    private Map<String, Integer> paths = new HashMap<>();

    public Dijkstra() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        nodeA.setDistance(0);
        nodeA.shortestPath.add(nodeA);
        calc(nodeA);

        System.out.print(nodeE.shortestPath.stream()
                .map(Node::getName)
                .reduce((a, b) -> a + b).get() + " - " + nodeE.getDistance());
    }

    private void calc(Node currentNode) {

        for (Map.Entry<Node, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
            Node node = entry.getKey();
            Integer value = entry.getValue();

            if (currentNode.distance + value < node.distance) {
                node.setDistance(currentNode.distance + value);
                node.shortestPath.clear();
                node.shortestPath.addAll(currentNode.shortestPath);
                node.shortestPath.add(node);
            }

            calc(entry.getKey());
        }

    }

    public static class Node {
        private String name;

        private List<Node> shortestPath = new LinkedList<>();

        private Integer distance = Integer.MAX_VALUE;
        private Map<Node, Integer> adjacentNodes = new HashMap<>();

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }
    }
}
