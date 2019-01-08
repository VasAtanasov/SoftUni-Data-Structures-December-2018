public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        // TODO
    }

    public ArrayStack(int capacity) {
        // TODO
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        // TODO
    }

    public T pop() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public T[] toArray() {
        // TODO
        throw new UnsupportedOperationException();
    }

    private void grow() {
        // TODO
        throw new UnsupportedOperationException();
    }

}