import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String INDENT = " ";
    private static BufferedReader reader;
    private static Map<Integer, Tree<Integer>> nodeByValue;

    private static class Tuple {
        private int height;
        private Tree<Integer> node;

        Tuple(int height, Tree<Integer> node) {
            this.height = height;
            this.node = node;
        }
    }

    private static class TreeSum {
        private int sum = 0;

        TreeSum(int sum) {
            this.sum = sum;
        }
    }

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        nodeByValue = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        readTree();
//        printRootNode();
//        printTree();
//        printLeafNodes();
//        printMiddleNodes();
//        printDeepestNode();
//        printLongestPath();
//        printAllPathsWithGivenSum();
//        printAllTreesWithGivenSum();
    }

    private static void printAllTreesWithGivenSum() throws IOException {
        int targetSum = Integer.parseInt(reader.readLine());
        System.out.println(String.format("Subtrees of sum %d:", targetSum));

        Deque<Tree<Integer>> nodes = new ArrayDeque<>();
        nodes.addLast(getRootNode());

        while (! nodes.isEmpty()) {
            Tree<Integer> currentRoot = nodes.removeFirst();
            TreeSum sum = new TreeSum(currentRoot.getValue());
            if (sumTree(sum, currentRoot, targetSum)) {
                StringBuilder sb = new StringBuilder();
                String result = printPreOrder(sb, currentRoot);
                System.out.println(result);
            }
            nodes.addAll(currentRoot.getChildren());
        }
    }

    private static String printPreOrder(StringBuilder builder, Tree<Integer> node) {
        builder.append(node.getValue()).append(" ");

        node.getChildren().forEach(child -> {
            printPreOrder(builder, child);
        });

        return builder.toString().trim();
    }


    private static boolean sumTree(TreeSum treeSum, Tree<Integer> node, int targetSum) {
        node.getChildren().forEach(child -> {
            treeSum.sum += child.getValue();
            sumTree(treeSum, child, targetSum);
        });

        return targetSum == treeSum.sum;
    }

    private static void printAllPathsWithGivenSum() throws IOException {
        int targetSum = Integer.parseInt(reader.readLine());
        System.out.println(String.format("Paths of sum %d:", targetSum));

        Tree<Integer> current = getRootNode();
        sumNodes(current, current.getValue(), targetSum);
    }

    private static void sumNodes(Tree<Integer> node, int sum, int targetSum) {
        if (node == null) {
            return;
        }

        node.getChildren().forEach(child -> {
            sumNodes(child, sum + child.getValue(), targetSum);
        });

        if (sum == targetSum) {
            Deque<Integer> nodesValues = getPathLeafToRoot(node);
            printValuesList("%s", nodesValues);
        }
    }

    private static Deque<Integer> getPathLeafToRoot(Tree<Integer> node) {
        Deque<Integer> nodesValues = new ArrayDeque<>();
        Tree<Integer> current = node;
        while (current.getParen() != null) {
            nodesValues.addFirst(current.getValue());
            current = current.getParen();
        }
        nodesValues.addFirst(current.getValue());
        return nodesValues;
    }

    private static void printValuesList(String text, Collection<Integer> values) {
        System.out.println(
                String.format(text,
                        String.join(" ",
                                values.stream()
                                        .map(Objects::toString)
                                        .toArray(String[]::new)
                        )
                )
        );
    }

    private static void printLongestPath() {
        Tuple deepest = new Tuple(0, getRootNode());
        getDeepestNode(new Tuple(0, getRootNode()), deepest);
        Deque<Integer> nodesValues = getPathLeafToRoot(deepest.node);
        printValuesList("Longest path: %s", nodesValues);
    }

    private static void printDeepestNode() {
        Tuple deepest = new Tuple(0, getRootNode());
        getDeepestNode(new Tuple(0, getRootNode()), deepest);
        System.out.println(String.format("Deepest node: %d", deepest.node.getValue()));
    }

    private static void getDeepestNode(Tuple tuple, Tuple deepest) {
        if (tuple.node == null) {
            return;
        }

        tuple.node.getChildren().forEach(child -> {
            getDeepestNode(new Tuple(tuple.height + 1, child), deepest);
        });

        if (tuple.node.getChildren().isEmpty() && tuple.height > deepest.height) {
            deepest.node = tuple.node;
            deepest.height = tuple.height;
        }
    }


    private static void printMiddleNodes() {
        String middleNodes = nodeByValue
                .values()
                .stream()
                .filter(node -> node.getParen() != null && ! node.getChildren().isEmpty())
                .map(Tree::getValue)
                .sorted()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
        System.out.println(String.format("Middle nodes: %s", middleNodes));
    }

    private static void printRootNode() {
        Tree<Integer> rootNode = getRootNode();
        System.out.println(String.format("Root node: %d", rootNode.getValue()));
    }

    private static Tree<Integer> getRootNode() {
        return nodeByValue
                .values()
                .stream()
                .filter(node -> node.getParen() == null)
                .findFirst()
                .orElse(null);
    }

    private static void printTree() {
        Tree<Integer> rootNode = getRootNode();
        StringBuilder sb = new StringBuilder();
        String result = printIndentedPreOrder(0, sb, rootNode);
        System.out.println(result);
    }

    private static String printIndentedPreOrder(int indent, StringBuilder builder, Tree<Integer> node) {
        for (int i = 0; i < indent * 2; i++) {
            builder.append(INDENT);
        }
        builder.append(node.getValue()).append("\n");

        node.getChildren().forEach(child -> {
            printIndentedPreOrder(indent + 1, builder, child);
        });

        return builder.toString();
    }

    private static void printLeafNodes() {
        Deque<Integer> queue = new LinkedList<>();
        getLeafNodes(getRootNode(), queue);
        System.out.println(
                String.format("Leaf nodes: %s",
                        String.join(" ",
                                queue.stream()
                                        .sorted()
                                        .map(Objects::toString)
                                        .toArray(String[]::new)
                        )
                )
        );
    }

    private static void getLeafNodes(Tree<Integer> node, Deque<Integer> queue) {
        if (node == null) {
            return;
        }

        node.getChildren().forEach(child -> {
            getLeafNodes(child, queue);
        });

        if (node.getChildren().isEmpty()) {
            queue.addLast(node.getValue());
        }
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
