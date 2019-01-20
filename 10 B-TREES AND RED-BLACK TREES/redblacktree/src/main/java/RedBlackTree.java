import java.util.*;
import java.util.function.Consumer;

public class RedBlackTree<T extends Comparable<T>> {

    private final boolean RED = true;
    private final boolean BLACK = false;

    private Node root;

    public RedBlackTree() {
    }

    private RedBlackTree(Node node) {
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

    public int getNodesCount() {
        return this.getNodesCount(this.root);
    }

    private int getNodesCount(Node node) {
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

        if (isRed(node.right) && ! isRed(node.left)) {
            node = this.rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = this.rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            this.flipColors(node);
        }


        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return node;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.color = RED;
        temp.color = BLACK;
        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;
        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return temp;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    public boolean contains(T value) {
        return this.findElement(value) != null;
    }

    public RedBlackTree<T> search(T item) {
        return new RedBlackTree<>(this.findElement(item));
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
        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
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
        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
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

        node.count = getNodesCount(node.left) + getNodesCount(node.right) + 1;
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
            return 1 + this.getNodesCount(node.left) + this.rank(node.right, item);
        }

        return this.getNodesCount(node.left);
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
        private boolean color;
        private int count;

        public Node(T value) {
            this.count = 1;
            this.value = value;
            this.color = RED;
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

        public boolean isColor() {
            return this.color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }
}

