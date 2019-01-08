import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class UnitTestLinkedStack {

    @Test
    public void push_should_increase_stack_size() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        for (int i = 0; i < 5; i++) {
            linkedStack.push(i);
        }

        Assert.assertEquals(5, linkedStack.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_pop_empty_stack() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.pop();
    }

    @Test
    public void pop_should_decrease_stack_size() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        for (int i = 0; i < 5; i++) {
            linkedStack.push(i);
        }

        linkedStack.pop();

        Assert.assertEquals(4, linkedStack.size());
    }

    @Test
    public void empty_the_stack() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        for (int i = 0; i < 5; i++) {
            linkedStack.push(i);
        }

        while (! linkedStack.isEmpty()) {
            linkedStack.pop();
        }

        Assert.assertEquals(0, linkedStack.size());
    }

    @Test
    public void test_toArray_method() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        for (int i = 0; i < 5; i++) {
            linkedStack.push(i);
        }

        Assert.assertEquals("[4, 3, 2, 1, 0]", Arrays.toString(linkedStack.toArray()));


    }
}
