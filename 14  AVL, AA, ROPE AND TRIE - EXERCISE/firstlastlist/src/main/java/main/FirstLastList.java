package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private List<T> elements;
    private Node root;

    public FirstLastList() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.root = this.insert(this.root, element);
    }

    private Node insert(Node node, T element) {
        if (node == null) {
            return new Node(element);
        }

        int cmp = element.compareTo(node.value);

        if (cmp <= 0) {
            node.left = this.insert(node.left, element);
        } else {
            node.right = this.insert(node.right, element);
        }

        return node;
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if (this.elements.size() < count) {
            throw new IllegalArgumentException();
        }
        return getElements(count, 0, x -> x += 1, this.elements);
    }

    @Override
    public Iterable<T> last(int count) {
        if (this.elements.size() < count) {
            throw new IllegalArgumentException();
        }
        return getElements(count, this.elements.size() - 1, x -> x -= 1, this.elements);
    }

    private Iterable<T> getElements(int count, int startIdx, Function<Integer, Integer> operation, List<T> collection) {
        List<T> result = new ArrayList<>();
        int idx = startIdx;
        while (count-- > 0) {
            result.add(collection.get(idx));
            idx = operation.apply(idx);
        }
        return result;
    }

    @Override
    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    @Override
    public Iterable<T> min(int count) {
        if (this.elements.size() < count) {
            throw new IllegalArgumentException();
        }
        List<T> allNodes = getAllNodes();
        return this.getElements(count, 0, x -> x += 1, allNodes);
    }

    @Override
    public Iterable<T> max(int count) {
        if (this.elements.size() < count) {
            throw new IllegalArgumentException();
        }
        List<T> allNodes = getAllNodes();
        return this.getElements(count, allNodes.size() - 1, x -> x -= 1, allNodes);
    }

    private List<T> getAllNodes() {
        List<T> allNodes = new ArrayList<>();
        this.eachInOrder(allNodes::add);
        return allNodes;
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public int removeAll(T element) {
        if (this.elements.size() == 0) {
            return 0;
        }
        int count = 0;
        List<T> newElements = new ArrayList<>();
        for (T value : this.elements) {
            if (value.compareTo(element) != 0) {
                newElements.add(value);
            } else {
                count++;
            }
        }

        this.elements = newElements;

        this.root = null;
        for (T value : this.elements) {
            this.root = this.insert(this.root, value);
        }
        return count;
    }

    private class Node implements Comparable<Node> {

        private T value;
        private Node left;
        private Node right;

        Node(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node otherNode) {
            return this.value.compareTo(otherNode.value);
        }
    }
}