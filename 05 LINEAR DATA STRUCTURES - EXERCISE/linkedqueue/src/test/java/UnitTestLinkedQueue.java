import org.junit.Assert;
import org.junit.Test;

public class UnitTestLinkedQueue {

    @Test
    public void enqueue_emptyQueue_shouldAddElement() {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

        linkedQueue.enqueue(5);
        linkedQueue.enqueue(6);
        int expectedSize = 2;
        Assert.assertEquals(expectedSize, linkedQueue.size());
    }

    @Test
    public void dequeue_oneElement_shouldMakeQueueEmpty() {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

        linkedQueue.enqueue(5);

        int element = linkedQueue.dequeue();

        int expectedElement = 5;
        int expectedSize = 0;
        Assert.assertEquals(expectedElement, element);
        Assert.assertEquals(expectedSize, linkedQueue.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dequeue_onEmptyQueue_shouldThrowException() {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.dequeue();
    }


}
