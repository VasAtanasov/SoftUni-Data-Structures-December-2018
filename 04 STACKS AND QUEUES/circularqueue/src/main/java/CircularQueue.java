public class CircularQueue<E> {

    private static final int INITIAL_CAPACITY = 16;

    private int size;
    private E[] elements;
    private int startIndex;
    private int endIndex;

    public CircularQueue() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CircularQueue(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
        this.size = 0;
        this.startIndex = 0;
        this.endIndex = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (this.size >= this.elements.length) {
            this.grow();
        }
        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newElements = (E[]) new Object[this.elements.length * 2];
        this.copyAllElementsTo(newElements);
        this.elements = newElements;
        this.startIndex = 0;
        this.endIndex = this.size;
    }

    private void copyAllElementsTo(E[] newElements) {
        int sourceIndex = this.startIndex;
        int destinationIndex = 0;
        for (int i = 0; i < this.size; i++) {
            newElements[destinationIndex] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.length;
            destinationIndex++;
        }
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException("The queue is empty!");
        }
        E result = this.elements[this.startIndex];
        this.startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;
        return result;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] resultArray = (E[]) new Object[this.size];
        this.copyAllElementsTo(resultArray);
        return resultArray;
    }

}
