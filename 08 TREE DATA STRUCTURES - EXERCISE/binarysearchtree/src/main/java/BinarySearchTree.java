import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node node) {
        this.preOrderCopy(node);
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

//    Iterable implementation of insert
//    public void insert(T value) {
//        if (this.root == null) {
//            this.root =  new Node(value);
//            return;
//        }
//
//        Node current = this.root;
//        Node parent = null;
//
//        while (current != null) {
//            parent = current;
//            if (value.compareTo(current.value) < 0) {
//                current = current.left;
//            } else if (value.compareTo(current.value) > 0) {
//                current = current.right;
//            } else {
//                return;
//            }
//        }
//
//        current = new Node(value);
//
//        if (parent.value.compareTo(value) > 0) {
//            parent.left = current;
//        } else {
//            parent.right = current;
//        }
//    }

    public void insert(T value) {
        this.root = this.insert(this.root, value);
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            this.nodesCount++;
            return new Node(value);
        }

        if (node.value.compareTo(value) > 0) {
            node.left = this.insert(node.left, value);
        } else {
            node.right = this.insert(node.right, value);
        }

        return node;
    }

    public boolean contains(T value) {
        return this.findElement(value) != null;
    }

    public BinarySearchTree<T> search(T item) {
        return new BinarySearchTree<>(this.findElement(item));
    }

    private Node findElement(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return current;
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

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node parent = null;
        Node current = this.root;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = current.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        throw new UnsupportedOperationException();
    }

    public T ceil(T element) {
        throw new UnsupportedOperationException();
    }

    public T floor(T element) {
        throw new UnsupportedOperationException();
    }

    public void delete(T key) {
        throw new UnsupportedOperationException();
    }

    public int rank(T item) {
        throw new UnsupportedOperationException();
    }

    public T select(int n) {
        throw new UnsupportedOperationException();
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

