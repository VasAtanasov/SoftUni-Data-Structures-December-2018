import java.util.Iterator;

public class ReversedList<T> implements Iterable<T> {
    private class ReversedIterator implements Iterator<T> {

        private int currentIndex;

        public ReversedIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex < count() && elements[this.currentIndex] != null;
        }

        @Override
        public T next() {
            return elements[this.currentIndex++];
        }
    }

    private static final int INITIAL_CAPACITY = 2;

    private T[] elements;

    private int count;

    @SuppressWarnings("MoveFieldAssignmentToInitializer")
    public ReversedList() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.setCount(0);
    }

    private void setCount(int count) {
        this.count = count;
    }

    private void resize() {
        T[] newElements = (T[])new Object[this.elements.length * 2];

        for (int i = 0; i < this.elements.length; i++) {
            newElements[i] = this.elements[i];
        }

        this.elements = newElements;
    }

    private void shift(int index) {
        for (int i = index; i < this.count(); i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void moveElements() {
        for (int i = this.count; i > 0; i--) {
            this.elements[i] = this.elements[i - 1];
        }
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.elements.length;
    }

    public T get(int index) {
        if(index < 0 || index >= this.count()) {
            throw new IllegalArgumentException();
        }

        return this.elements[index];
    }

    public void set(int index, T element) {
        if(index < 0 || index >= this.count()) {
            throw new IllegalArgumentException();
        }

        this.elements[index] = element;
    }

    public void add(T element) {
        if(this.count() >= this.elements.length) {
            this.resize();
        }

        this.moveElements();
        this.elements[0] = element;
        this.setCount(this.count() + 1);
    }

    public T removeAt(int index) {
        if(index < 0 || index >= this.count()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T element = this.elements[index];
        this.shift(index);
        this.setCount(this.count() - 1);

        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReversedIterator();
    }

}
