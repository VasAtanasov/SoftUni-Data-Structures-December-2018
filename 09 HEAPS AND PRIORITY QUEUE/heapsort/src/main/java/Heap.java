public class Heap {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable> void sort(T[] array) {
        constructHeap(array);
    }

    private static <T extends Comparable<T>> void constructHeap(T[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            heapifyDown(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            heapifyDown(array, 0, i);
        }
    }

    private static <T extends Comparable<T>> void heapifyDown(T[] array, int index, int length) {
        while(index < length/2){
            T element = array[index];
            int child = index*2 + 1;
            if (length > index*2 + 2 && array[index*2 + 2].compareTo(array[child]) > 0){
                child++;
            }
            if (element.compareTo(array[child]) < 0){
                swap(array, index, child);
                index = child;
            }else{
                break;
            }
        }
    }

    private static <T> void swap(T[] heap, int parentIndex, int childIndex) {
        T temp = heap[parentIndex];
        heap[parentIndex] = heap[childIndex];
        heap[childIndex] = temp;
    }

    private static int left(int index) {
        return (2 * index) + 1;
    }

    private static <T extends Comparable<T>> boolean isLess(T[] heap, int parentIndex, int childIndex) {
        return heap[parentIndex].compareTo(heap[childIndex]) < 0;
    }
}
