package main;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T> {
    private Node root;
    private Map<T, Node> nodesByValue;

    public Hierarchy(T element) {
        this.root = new Node(element);
        this.nodesByValue = new LinkedHashMap<>() {{
            put(element, root);
        }};
    }

    public void Add(T parent, T child) {
        if (! this.nodesByValue.containsKey(parent)) {
            throw new IllegalArgumentException();
        }

        if (this.nodesByValue.containsKey(child)) {
            throw new IllegalArgumentException();
        }

        Node parentNode = this.nodesByValue.get(parent);
        Node childNode = new Node(child);
        childNode.setParent(parentNode);

        parentNode.getChildren().add(childNode);
        this.nodesByValue.put(child, childNode);
    }

    public int getCount() {
        return this.nodesByValue.size();
    }

    public void Remove(T element) {
        if (! this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException();
        }

        Node nodeToDelete = this.nodesByValue.get(element);

        if (nodeToDelete.getParent() == null) {
            throw new IllegalStateException();
        }

        Node parentNode = nodeToDelete.getParent();

        parentNode.getChildren().remove(nodeToDelete);
        this.nodesByValue.remove(element);

        List<Node> children = nodeToDelete.getChildren();
        children.forEach(node -> node.setParent(parentNode));
        parentNode.getChildren().addAll(children);
    }

    public boolean Contains(T element) {
        return this.nodesByValue.containsKey(element);
    }

    public T GetParent(T element) {
        if (! this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException();
        }

        Node node = this.nodesByValue.get(element);

        if (node.getParent() == null) {
            return null;
        }

        return node.getParent().getValue();
    }

    public Iterable<T> getChildren(T element) {
        if (! this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException();
        }

        Node parent = this.nodesByValue.get(element);

        return parent
                .getChildren()
                .stream()
                .map(Node::getValue)
                .collect(Collectors.toList());
    }

    public Iterable<T> GetCommonElements(IHierarchy<T> other) {
        List<T> elements = new ArrayList<>();
        this.nodesByValue.keySet().forEach(element -> {
            if (other.Contains(element)) {
                elements.add(element);
            }
        });

        return elements;
    }

    @Override
    public Iterator<T> iterator() {
        Deque<Node> queue = new ArrayDeque<>();
        List<T> allElements = new ArrayList<>();
        queue.push(this.root);
        while (! queue.isEmpty()) {
            Node currentNode = queue.removeFirst();
            allElements.add(currentNode.getValue());
            queue.addAll(currentNode.getChildren());
        }
        return allElements.iterator();
    }

    private class HierarchyIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    private class Node {

        private T value;
        private Node parent;
        private List<Node> children;

        public Node(T value) {
            this.value = value;
            this.parent = null;
            this.children = new ArrayList<>();
        }

        public Node getParent() {
            return this.parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public List<Node> getChildren() {
            return this.children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }
}
