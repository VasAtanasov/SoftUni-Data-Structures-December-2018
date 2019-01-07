import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        // TODO
    }

    public void addLast(E item) {
        // TODO
    }

    public E removeFirst() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public E removeLast() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
