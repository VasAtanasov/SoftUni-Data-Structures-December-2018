import java.util.function.Consumer;

public class IntervalTree {

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }

    private Node root;

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {
        throw new UnsupportedOperationException();
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        throw new UnsupportedOperationException();
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }

        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = insert(node.right, lo, hi);
        }

        return node;
    }
}
