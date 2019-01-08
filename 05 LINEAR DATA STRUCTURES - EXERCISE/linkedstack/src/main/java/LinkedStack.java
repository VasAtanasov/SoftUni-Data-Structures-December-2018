public class LinkedStack<E> {

    private Node firstNode;
    private int size;


    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        Node node = new Node(element, null);
        if (this.firstNode != null) {
            node.nextNode = this.firstNode;
        }
        this.firstNode = node;
        this.size++;
    }

    public E pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E value = this.firstNode.value;
        this.firstNode = this.firstNode.nextNode;
        this.size--;
        return value;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];
        int index = 0;
        Node current = this.firstNode;
        while (current != null) {
            result[index] = current.value;
            current = current.nextNode;
            index++;
        }
        return result;
    }

    private class Node {

        private E value;
        private Node nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node nextNode) {
            this(value);
            this.nextNode = nextNode;
        }

        public Node getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}