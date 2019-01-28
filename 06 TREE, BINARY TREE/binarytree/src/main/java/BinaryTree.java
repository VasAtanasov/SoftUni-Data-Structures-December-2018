import java.util.function.Consumer;

public class BinaryTree<T> {

    private static final String INDENT = " ";

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.leftChild = this.rightChild = child;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this(value);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        for (int i = 0; i < indent * 2; i++) {
            builder.append(INDENT);
        }

        builder.append(this.value).append("\n");

        if (this.leftChild != null) {
            this.leftChild.printIndentedPreOrder(indent + 1, builder);
        }
        if (this.rightChild != null) {
            this.rightChild.printIndentedPreOrder(indent + 1, builder);
        }

        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }
        consumer.accept(this.value);
        if (this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }
        if (this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }
        consumer.accept(this.value);
    }
}
