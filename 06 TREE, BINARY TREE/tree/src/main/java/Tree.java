import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private static final String INDENT = " ";

    private T value;
    private List<Tree<T>> children;

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        Collections.addAll(this.children, children);
    }

    public String print(int indent, StringBuilder builder) {
        this.print(this, indent, builder);
        return builder.toString();
    }

    private void print(Tree<T> node, int indent, StringBuilder builder) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < indent * 2; i++) {
            builder.append(INDENT);
        }
        builder.append(node.value).append("\n");
        indent++;
        for (Tree<T> child : node.children) {
            this.print(child, indent, builder);
        }
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : this.children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<>();
        this.DFS(this, result);
        return result;
    }

    private void DFS(Tree<T> tree, List<T> result) {
        for (Tree<T> child : tree.children) {
            this.DFS(child, result);
        }
        result.add(tree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<>();
        Deque<Tree<T>> nodes = new ArrayDeque<>();
        nodes.addLast(this);

        while (! nodes.isEmpty()) {
            Tree<T> current = nodes.removeFirst();
            result.add(current.value);
            for (Tree<T> child : current.children) {
                nodes.addLast(child);
            }
        }
        return result;
    }

}