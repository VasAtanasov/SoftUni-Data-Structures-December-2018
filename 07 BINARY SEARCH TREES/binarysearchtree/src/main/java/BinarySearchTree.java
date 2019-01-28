import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Node root) {
        this.copy(root);
    }

    private void copy(Node node) {
        if (node == null) {
            return;
        }
        this.insert(node.value);
        this.copy(node.left);
        this.copy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        this.root = this.insert(this.root, value);
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        int compare = node.value.compareTo(value);

        if (compare > 0) {
            node.left = this.insert(node.left, value);
        } else {
            node.right = this.insert(node.right, value);
        }

        return node;
    }

    public boolean contains(T value) {
        return this.contains(this.root, value);
    }

    private boolean contains(Node node, T value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        } else if (node.value.compareTo(value) > 0) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            int compare = current.value.compareTo(item);
            if (compare > 0) {
                current = current.left;
            } else if (compare < 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        Node parent = null;
        Node min = this.root;
        while (min.left != null) {
            parent = min;
            min = min.left;
        }

        if (parent == null) {
            this.root = min.right;
        } else {
            parent.left = min.right;
        }
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new ArrayDeque<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }
        int compareLow = startRange.compareTo(node.value);
        int compareHigh = endRange.compareTo(node.value);

        if (compareLow < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareLow <= 0 && compareHigh >= 0) {
            queue.addLast(node.value);
        }
        if (compareHigh > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

