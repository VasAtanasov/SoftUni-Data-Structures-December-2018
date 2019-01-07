import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(E item) {
        Node old = head;

        this.head = new Node(item);
        this.head.next = old;

        if (this.isEmpty()) {
            this.tail = this.head;
        }

        this.size++;
    }

    public void addLast(E item) {
        Node old = this.tail;

        this.tail = new Node(item);

        if (this.isEmpty()) {
            this.head = this.tail;
        } else {
            old.next = this.tail;
        }

        this.size++;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E item = this.head.value;

        this.head = this.head.next;
        this.size--;

        if (this.isEmpty()) {
            this.tail = null;
        }

        return item;
    }

    public E removeLast() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E item = this.tail.value;

        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node secondToLast = this.getSecondToLast();
            secondToLast.next = null;
            this.tail = secondToLast;
        }

        this.size--;

        return item;
    }

    private Node getSecondToLast() {
        Node secondToLast = this.head;
        for (int i = 0; i < this.size - 2; i++) {
            secondToLast = secondToLast.next;
        }
        return secondToLast;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (! hasNext()) {
                throw new NoSuchElementException("Iterator exceeded.");
            }

            E ret = current.value;
            current = current.next;
            return ret;
        }

    }

    private class Node {

        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


}
