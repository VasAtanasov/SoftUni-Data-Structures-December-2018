import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return (2 * index) + 1;
    }

    private int right(int index) {
        return (2 * index) + 2;
    }

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp();
    }

    private void heapifyUp() {
        int index = this.heap.size() - 1;
        while (index > 0 && isLess(parent(index), index)) {
            this.swap(this.parent(index), index);
            index = parent(index);
        }
    }

    private boolean isLess(int parentIndex, int childIndex) {
        return this.heap.get(parentIndex).compareTo(this.heap.get(childIndex)) < 0;
    }

    private void swap(int parentIndex, int childIndex) {
        T temp = this.heap.get(parentIndex);
        this.heap.set(parentIndex, this.heap.get(childIndex));
        this.heap.set(childIndex, temp);
    }


    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        return this.heap.get(0);
    }

    public T pull() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T element = this.heap.get(0);

        this.swap(0, this.size() - 1);
        this.heap.remove(this.size() - 1);
        this.heapifyDown();
        return element;
    }

    private void heapifyDown() {
        int index = 0;
        int child = this.left(index);
        while (index < this.size() / 2) {
            if (hasChild(child + 1) && isLess(child, child + 1)) {
                child = child + 1;
            }

            if (isLess(child, index)) {
                break;
            }
            this.swap(index, child);
            index = child;
        }
    }

    private boolean hasChild(int index) {
        return this.size() > index * 2 + 2;
    }
}
