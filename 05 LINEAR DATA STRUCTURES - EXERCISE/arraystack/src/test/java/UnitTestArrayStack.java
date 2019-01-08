import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class UnitTestArrayStack {
    @Test
    public void push_should_increase_size() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        arrayStack.push(1);
        arrayStack.push(2);

        Assert.assertEquals(2, arrayStack.size());
    }

    @Test
    public void test_growing_of_array() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        for (int i = 0; i < 20; i++) {
            arrayStack.push(i);
        }

        Assert.assertEquals(20, arrayStack.size());
    }

    @Test
    public void pop_should_decrease_size() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        for (int i = 0; i < 6; i++) {
            arrayStack.push(i);
        }

        Assert.assertEquals(6, arrayStack.size());

        arrayStack.pop();

        Assert.assertEquals(5, arrayStack.size());
    }

    @Test
    public void test_toArray_method() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        for (int i = 0; i < 6; i++) {
            arrayStack.push(i);
        }

        Assert.assertEquals("[5, 4, 3, 2, 1, 0]", Arrays.toString(arrayStack.toArray()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_with_pop_on_empty_stack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        arrayStack.pop();
    }


}
