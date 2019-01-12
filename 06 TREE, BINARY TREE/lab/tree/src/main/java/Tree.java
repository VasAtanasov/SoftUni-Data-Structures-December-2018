import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private static final String INDENT = " ";

    private T value;
    private List<Tree<T>> children;

    public Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this(value);
        Collections.addAll(this.children, children);
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        for (int i = 0; i < indent * 2; i++) {
            builder.append(INDENT);
        }
        builder.append(this.value)
                .append("\n");
        for (Tree<T> child : this.children) {
            child.print(indent + 1, builder);
        }
        return builder.toString();
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
        Deque<Tree<T>> queue = new ArrayDeque<>();
        queue.addLast(this);
        while (! queue.isEmpty()) {
            Tree<T> node = queue.removeFirst();
            result.add(node.value);
            for (Tree<T> child : node.children) {
                queue.addLast(child);
            }
        }
        return result;
    }

}