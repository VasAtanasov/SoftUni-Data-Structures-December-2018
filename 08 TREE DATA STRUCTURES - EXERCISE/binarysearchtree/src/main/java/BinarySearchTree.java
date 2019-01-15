import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

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

    public int count() {
        return this.count(this.root);
    }

    private int count(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
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
            return new Node(value);
        }

        if (node.value.compareTo(value) > 0) {
            node.left = this.insert(node.left, value);
        } else if (node.value.compareTo(value) < 0) {
            node.right = this.insert(node.right, value);
        }

        node.count = 1 + this.count(node.left) + this.count(node.right);
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

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findMin(node.left);
    }

//    public void deleteMin() {
//        if (this.root == null) {
//            throw new IllegalArgumentException();
//        }
//
//        Node parent = null;
//        Node current = this.root;
//
//        while (current.left != null) {
//            parent = current;
//            current = current.left;
//        }
//
//        if (parent == null) {
//            this.root = this.root.right;
//        } else {
//            parent.left = current.right;
//        }
//    }

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
        node.count = 1 + this.count(node.left) + this.count(node.right);
        return node;
    }

//    public void deleteMax() {
//        if (this.root == null) {
//            throw new IllegalArgumentException();
//        }
//        Node parent = null;
//        Node max = this.root;
//        while (max.getRight() != null) {
//            parent = max;
//            max = max.right;
//        }
//        if (parent == null) {
//            this.root = this.root.left;
//        } else {
//            parent.right = max.left;
//        }
//    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        this.root = this.deleteMax(this.root);
    }

    private Node deleteMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.left;
        }
        node.right = this.deleteMax(node.right);
        node.count = 1 + this.count(node.left) + this.count(node.right);
        return node;
    }

    @SuppressWarnings("Duplicates")
    public T ceil(T element) {
        Node currentNode = this.root;
        T result = null;
        while (currentNode != null) {
            if (element.compareTo(currentNode.value) < 0) {
                result = currentNode.value;
                currentNode = currentNode.left;
            } else if (element.compareTo(currentNode.value) > 0) {
                currentNode = currentNode.right;
            } else {
                result = currentNode.value;
                break;
            }
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    public T floor(T element) {
        Node currentNode = this.root;
        T result = null;
        while (currentNode != null) {
            if (element.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else if (element.compareTo(currentNode.value) > 0) {
                result = currentNode.value;
                currentNode = currentNode.right;
            } else {
                result = currentNode.value;
                break;
            }
        }
        return result;
    }

    public void delete(T element) {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        this.root = delete(this.root, element);
    }

    private Node delete(Node node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);

        if (cmp < 0) {
            node.left = delete(node.left, item);
        } else if (cmp > 0) {
            node.right = delete(node.right, item);
        } else {
            if (node.right == null) {
                return node.left;
            }

            if (node.left == null) {
                return node.right;
            }

            Node temp = findMin(node.right);
            temp.right = deleteMin(node.right);
            temp.left = node.left;
            node = temp;
        }

        node.count = count(node.left) + count(node.right) + 1;
        return node;
    }

    public int rank(T item) {
        return this.rank(this.root, item);
    }

    private int rank(Node node, T item) {
        if (node == null) {
            return 0;
        }

        int compare = item.compareTo(node.value);

        if (compare < 0) {
            return this.rank(node.left, item);
        }
        if (compare > 0) {
            return 1 + this.count(node.left) + this.rank(node.right, item);
        }

        return this.count(node.left);
    }

    public T select(int n) {
        T result = null;
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        ArrayDeque<Node> nodeStack = new ArrayDeque<>();
        nodeStack.push(this.root);
        while (! nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pollLast();
            if (n == this.rank(currentNode.value)) {
                result = currentNode.value;
            }
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
            }
        }
        return result;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;
        private int count;

        public Node(T value) {
            this.count = 1;
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

