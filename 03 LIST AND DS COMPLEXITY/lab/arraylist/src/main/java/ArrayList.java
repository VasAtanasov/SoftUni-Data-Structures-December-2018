public class ArrayList<T> {
    private static final int INITIAL_CAPACITY = 2;

    private T[] items;
    private int cursor;


    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.cursor = 0;
    }

    public int getCount() {
        return this.cursor;
    }

    public T get(int index) {
        if (index < 0 || index >= this.items.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index >= cursor) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.items[index];
    }

    public void add(T item) {
        if (this.cursor == this.items.length) {
            this.resize();
        }
        this.items[this.cursor++] = item;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] resized = (T[]) new Object[this.items.length * 2];
        System.arraycopy(this.items, 0, resized, 0, this.items.length);
        this.items = resized;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= cursor) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T item = this.items[index];
        this.shift(index);
        this.cursor--;
        if (this.cursor <= this.items.length / 4) {
            this.shrink();
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        T[] resized = (T[]) new Object[this.items.length / 2];
        System.arraycopy(this.items, 0, resized, 0, resized.length);
        this.items = resized;
    }

    private void shift(int index) {
        for (int i = index; i < this.cursor; i++) {
            if (this.items[i] == null) {
                break;
            }
            this.items[i] = this.items[i + 1];
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.cursor; i++) {
            output.append(this.items[i]);
            if (i < this.cursor - 1) {
                output.append(" ");
            }
        }
        return output.toString();
    }
}
