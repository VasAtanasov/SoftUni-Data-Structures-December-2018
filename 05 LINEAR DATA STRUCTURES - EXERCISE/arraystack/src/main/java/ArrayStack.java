public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if (this.size >= this.elements.length) {
            this.grow();
        }
        this.elements[this.size++] = element;
    }

    public T pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        this.size--;
        T element = this.elements[this.size];
        this.elements[this.size] = null;
        return element;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] newArray = (T[]) new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            newArray[this.size - 1 - i] = this.elements[i];
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        T[] newArray = (T[]) new Object[this.elements.length * 2];
        System.arraycopy(this.elements, 0, newArray, 0, this.size);
        this.elements = newArray;
    }
}