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
        if (this.size == this.elements.length) {
            this.grow();
        }
        this.elements[endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        E element = this.elements[startIndex];
        this.startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;
        this.size--;
        if (this.size < this.elements.length / 4) {
            this.shrink();
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] resultArray = (E[]) new Object[this.size];
        this.copyElements(resultArray);
        return resultArray;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newArray = (E[]) new Object[this.elements.length * 2];
        this.copyElements(newArray);
        this.elements = newArray;
        this.startIndex = 0;
        this.endIndex = this.size;
    }

    private void copyElements(E[] newArray) {
        for (int destination = 0, sourceIndex = this.startIndex; destination < this.size; destination++) {
            newArray[destination] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.length;
        }
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        E[] newArray = (E[]) new Object[this.elements.length / 2];
        this.copyElements(newArray);
        this.startIndex = 0;
        this.endIndex = this.size;
        this.elements = newArray;
    }
}
