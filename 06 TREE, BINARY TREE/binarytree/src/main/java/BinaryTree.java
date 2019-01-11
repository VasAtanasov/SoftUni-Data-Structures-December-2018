import java.util.function.Consumer;

public class BinaryTree<T> {

    private static final String INDENT = " ";

    private T value;
    private BinaryTree<T> leftChiled;
    private BinaryTree<T> rightChiled;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.leftChiled = this.rightChiled = child;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this(value);
        this.leftChiled = leftChild;
        this.rightChiled = rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        for (int i = 0; i < indent * 2; i++) {
            builder.append(INDENT);
        }

        builder.append(this.value).append("\n");

        if (this.leftChiled != null) {
            this.leftChiled.printIndentedPreOrder(indent + 1, builder);
        }
        if (this.rightChiled != null) {
            this.rightChiled.printIndentedPreOrder(indent + 1, builder);
        }

        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if (this.leftChiled != null) {
            this.leftChiled.eachInOrder(consumer);
        }
        consumer.accept(this.value);
        if (this.rightChiled != null) {
            this.rightChiled.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if (this.leftChiled != null) {
            this.leftChiled.eachPostOrder(consumer);
        }
        if (this.rightChiled != null) {
            this.rightChiled.eachPostOrder(consumer);
        }
        consumer.accept(this.value);
    }
}
