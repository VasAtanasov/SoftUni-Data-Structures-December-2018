public class LinkedQueue<E> {

    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        // TODO
    }

    public E dequeue() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public E[] toArray() {
        // TODO
        throw new UnsupportedOperationException();
    }

    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}