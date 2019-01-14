import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static BufferedReader reader;
    private static Map<Integer, Tree<Integer>> nodeByValue;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        nodeByValue = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        readTree();

        findRootNode();
    }

    private static void findRootNode() {
        nodeByValue
                .values()
                .stream()
                .filter(node -> node.getParen() == null)
                .findFirst()
                .ifPresent(rootNode -> System.out.println(String.format("Root node: %d", rootNode.getValue())));
    }

    private static void addEdge(Integer parent, Integer child) {
        Tree<Integer> parentNode = getNodeByValue(parent);
        Tree<Integer> childNode = getNodeByValue(child);

        parentNode.getChildren().add(childNode);
        childNode.setParen(parentNode);
    }

    private static Tree<Integer> getNodeByValue(Integer value) {
        nodeByValue.putIfAbsent(value, new Tree<>(value));
        return nodeByValue.get(value);
    }

    private static void readTree() throws IOException {
        int numberOfNodes = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfNodes - 1; i++) {
            Integer[] tokens = Arrays
                    .stream(reader.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);

            Integer parent = tokens[0];
            Integer child = tokens[1];

            addEdge(parent, child);
        }
    }
}
