import java.util.function.Consumer;

public class BinaryTree<T> {
    private static final String INDENT = " ";

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this(value, null);
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this(value, child, child);
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        this.printIndentedPreOrder(this, indent, builder);
        return builder.toString();
    }

    private void printIndentedPreOrder(BinaryTree<T> node, int indent, StringBuilder builder) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < indent * 2; i++) {
            builder.append(INDENT);
        }
        builder.append(node.value).append("\n");
        this.printIndentedPreOrder(node.leftChild, indent + 1, builder);
        this.printIndentedPreOrder(node.rightChild, indent + 1, builder);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this, consumer);
    }

    private void eachInOrder(BinaryTree<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        this.eachInOrder(node.leftChild, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.rightChild, consumer);
    }

    public void eachPostOrder(Consumer<T> consumer) {
        this.eachPostOrder(this, consumer);
    }

    private void eachPostOrder(BinaryTree<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        this.eachPostOrder(node.leftChild, consumer);
        this.eachPostOrder(node.rightChild, consumer);
        consumer.accept(node.value);
    }
}
