import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E> {

    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(E element) {
        ListNode<E> newHead = new ListNode<>(element);
        if (this.size == 0) {
            this.head = this.tail = newHead;
        } else {
            newHead.nextListNode = this.head;
            this.head.prevListNode = newHead;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(E element) {
        ListNode<E> newTail = new ListNode<>(element);
        if (this.size == 0) {
            this.head = this.tail = newTail;
        } else {
            newTail.prevListNode = this.tail;
            this.tail.nextListNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty list");
        }
        E value = this.head.value;
        this.head = this.head.nextListNode;
        if (this.head != null) {
            this.head.prevListNode = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return value;
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty list");
        }
        E value = this.tail.value;
        this.tail = this.tail.prevListNode;
        if (this.tail != null) {
            this.tail.nextListNode = null;
        } else {
            this.head = null;
        }
        this.size--;
        return value;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        int idx[] = {0};
        this.forEach(e -> array[idx[0]++] = e);
        return array;
    }

    private class ListNode<E> {

        private E value;
        private ListNode<E> prevListNode;
        private ListNode<E> nextListNode;

        public ListNode(E value) {
            this.value = value;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        ListNode<E> currentNode = this.head;
        while (currentNode != null) {
            action.accept(currentNode.value);
            currentNode = currentNode.nextListNode;
        }
    }


    private class MyIterator implements Iterator<E> {

        private int index;
        private ListNode<E> node;

        MyIterator() {
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
            this.node = this.node.nextListNode;
            return element;
        }

        @Override
        public void remove() {

        }
    }

}
