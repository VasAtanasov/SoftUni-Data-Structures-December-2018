import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E> {

    private Node head;
    private Node tail;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E element) {
        if (this.size == 0) {
            this.head = this.tail = new Node(element);
        } else {
            Node newHead = new Node(element);
            newHead.next = this.head;
            this.head.prev = newHead;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(E element) {
        if (this.size == 0) {
            this.head = this.tail = new Node(element);
        } else {
            Node newTail = new Node(element);
            newTail.prev = this.tail;
            this.tail.next = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        Node first = this.head;
        this.head = this.head.next;
        if (this.head != null) {
            this.head.prev = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return first.value;
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        Node last = this.tail;
        this.tail = this.tail.prev;
        if (this.tail != null) {
            this.tail.next = null;
        } else {
            this.head = null;
        }
        this.size--;
        return last.value;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        this.traverse(this.head, array, 0);
        return array;
    }

    private void traverse(Node node, E[] array, int index) {
        if (node == null) {
            return;
        }
        array[index++] = node.value;
        traverse(node.next, array, index);
    }

    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedListIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Node currentNode = this.head;
        while (currentNode != null) {
            action.accept(currentNode.value);
            currentNode = currentNode.next;
        }
    }

    private class DoubleLinkedListIterator implements Iterator<E> {

        private int index;
        private Node node;

        DoubleLinkedListIterator() {
            this.index = 0;
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public E next() {
            E element = this.node.value;
            this.index++;
            this.node = this.node.next;
            return element;
        }

        @Override
        public void remove() {

        }
    }

    private class Node {
        private E value;
        private Node next;
        private Node prev;

        public Node(E value) {
            this.value = value;
        }
    }

}
