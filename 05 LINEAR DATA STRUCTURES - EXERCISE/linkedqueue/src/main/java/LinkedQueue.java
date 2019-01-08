public class LinkedQueue<E> {

    private QueueNode head;
    private QueueNode tail;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        QueueNode newTail = new QueueNode(element);
        if (this.size == 0) {
            this.head = this.tail = newTail;
        } else {
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        E value = this.head.value;
        this.head = this.head.nextNode;
        if (this.head != null) {
            this.head.prevNode = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return value;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];
        int index = 0;
        QueueNode current = this.head;
        while (current != null) {
            result[index] = current.value;
            current = current.nextNode;
            index++;
        }
        return result;
    }

    private class QueueNode {
        private E value;
        private QueueNode nextNode;
        private QueueNode prevNode;

        QueueNode(E element) {
            this.value = element;
        }
    }
}