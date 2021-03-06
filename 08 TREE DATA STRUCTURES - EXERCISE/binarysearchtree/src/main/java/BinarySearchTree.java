import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.getNodesCount(this.root);
    }

    private int getNodesCount(Node node) {
        if (node == null) {
            return 0;
        }
        return node.childrenCount;
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

        node.childrenCount = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);

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

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node node) {
        T minv = node.value;
        while (node.left != null) {
            minv = node.left.value;
            node = node.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        this.root = this.deleteMin(this.root);
    }

    private Node deleteMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        node.left = this.deleteMin(node.left);
        node.childrenCount = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return node;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        this.root = deleteMax(this.root);
    }

    private Node deleteMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.left;
        }
        node.right = this.deleteMax(node.right);
        node.childrenCount = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return node;
    }

    public T ceil(T element) {
        Node node = this.ceil(this.root, element);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    public Node ceil(Node node, T element) {
        if (node == null) {
            return null;
        }

        if (node.value.compareTo(element) == 0) {
            return node;
        }
        if (node.value.compareTo(element) > 0) {
            Node left = ceil(node.left, element);
            return Objects.requireNonNullElse(left, node);
        }

        return ceil(node.right, element);
    }

    public T floor(T element) {
        Node node = this.floor(this.root, element);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    public Node floor(Node node, T element) {
        if (node == null) {
            return null;
        }
        if (node.value == element) {
            return node;
        }
        if (node.value.compareTo(element) > 0) {
            return floor(node.left, element);
        }
        Node right = floor(node.right, element);
        if (right == null) {
            return node;
        } else {
            return right;
        }
    }

    public void delete(T element) {
        this.root = this.delete(this.root, element);

    }

    private Node delete(Node node, T element) {
        if (node == null) {
            return null;
        }

        int compare = node.value.compareTo(element);

        if (compare > 0) {
            node.left = this.delete(node.left, element);
        } else if (compare < 0) {
            node.right = this.delete(node.right, element);
        } else {

            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            node.value = this.minValue(node.right);

            node.right = this.delete(node.right, node.value);
        }

        node.childrenCount = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);

        return node;
    }

    public int rank(T element) {
        return rank(this.root, element);
    }

    private int rank(Node node, T element) {
        if (node == null) {
            return 0;
        }

        int compare = node.value.compareTo(element);

        if (compare > 0) {
            return this.rank(node.left, element);
        }

        if (compare < 0) {
            return 1 + this.getNodesCount(node.left) + this.rank(node.right, element);
        }

        return this.getNodesCount(node.left);
    }

    public T select(int rank) {
        Node node = this.select(this.root, rank);

        if (node == null) {
            return null;
        }

        return node.value;
    }

    private Node select(Node node, int rank) {
        if (node == null) {
            return null;
        }

        int leftCount = this.getNodesCount(node.left);

        if (leftCount > rank) {
            return this.select(node.left, rank);
        } else if (leftCount < rank) {
            return this.select(node.right, rank - (leftCount + 1));
        }

        return node;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
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

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

