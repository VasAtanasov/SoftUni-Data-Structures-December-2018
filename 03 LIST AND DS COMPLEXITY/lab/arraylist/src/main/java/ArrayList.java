public class ArrayList<T> {
    private static final int INITIAL_CAPACITY = 4;

    private T[] items;
    private int count;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.items = (T[]) new Object[capacity];
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public T get(int index) {
        if (isOutOfBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.items[index];
    }

    public void add(T item) {
        if (this.count == this.items.length) {
            this.grow();
        }
        this.items[this.count++] = item;
    }

    public T removeAt(int index) {
        if (isOutOfBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T item = this.items[index];
        this.items[index] = null;
        this.shift(index);
        this.count--;
        if (this.count <= this.items.length / 4) {
            this.shrink();
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        T[] newArray = (T[]) new Object[this.items.length / 2];
        if (this.count >= 0) System.arraycopy(this.items, 0, newArray, 0, this.count);
        this.items = newArray;
    }

    private void shift(int index) {
        if (this.count - index >= 0) System.arraycopy(this.items, index + 1, this.items, index, this.count - index);
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        T[] newArray = (T[]) new Object[this.items.length * 2];
        System.arraycopy(this.items, 0, newArray, 0, this.items.length);
        this.items = newArray;
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= this.count;
    }

}
