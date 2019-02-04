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

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && this.isLess(this.getParentIndex(index), index)) {
            this.swap(index, this.getParentIndex(index));
            index = this.getParentIndex(index);
        }
    }

    private void swap(int index, int parentIndex) {
        T tmp = this.heap.get(parentIndex);
        this.heap.set(parentIndex, this.heap.get(index));
        this.heap.set(index, tmp);
    }

    private boolean isLess(int parentIndex, int index) {
        return this.heap.get(parentIndex).compareTo(this.heap.get(index)) < 0;
    }

    private int getParentIndex(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    public T peek() {
        if (this.heap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return this.heap.get(0);
    }

    public T pull() {
        if (this.heap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T item = this.heap.get(0);
        this.swap(0, this.size() - 1);
        this.heap.remove(this.size() - 1);
        this.heapifyDown(0);

        return item;
    }

    private void heapifyDown(int index) {
        while (index < this.size() / 2) {
            int child = this.getLeftChildIndex(index);
            if (this.hasChild(child + 1) && this.isLess(child, child + 1)) {
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
        return this.size() > this.getRightChildIndex(index);
    }
}
