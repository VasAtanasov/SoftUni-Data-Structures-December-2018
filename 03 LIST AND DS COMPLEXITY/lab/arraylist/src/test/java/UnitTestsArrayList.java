import org.junit.Assert;
import org.junit.Test;

public class UnitTestsArrayList {


    @Test
    public void AddSingleElementShouldIncreaseCount() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);

        Assert.assertEquals(1, list.getCount());
    }

    @Test
    public void AddMultipleElementShouldIncreaseCount() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Assert.assertEquals(100, list.getCount());
    }

    @Test
    public void AddAndGetSingleElement() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(5);
        int element = list.get(0);
        Assert.assertEquals(5, element);
    }

    @Test
    public void AddAndGetMultipleElements() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 0; i < 100; i++) {
            int element = list.get(i);

            Assert.assertEquals(i, element);
        }
    }

    @Test
    public void RemoveSingleElementShouldHaveCorrectCount() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(0);
        list.removeAt(0);

        Assert.assertEquals(0, list.getCount());
    }

    @Test
    public void RemoveSingleElementShouldHaveCorrectElements() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(0);
        list.add(1);
        list.add(2);
        list.removeAt(0);

        int firstElement = list.get(0);
        int secondElement = list.get(1);


        Assert.assertEquals(1, firstElement);
        Assert.assertEquals(2, secondElement);
    }

    @Test
    public void RemoveMultipleElements() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 0; i < 50; i++) {
            list.removeAt(0);
        }

        Assert.assertEquals(50, list.getCount());
        for (int i = 0; i < 50; i++) {
            int element = list.get(i);
            Assert.assertEquals(i + 50, element);
        }
    }

    @Test
    public void AddMultiplePerformanceTest() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        for (int i = 0; i < 100000; i++) {
            int element = list.get(i);
            Assert.assertEquals(i, element);
        }
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void SetInvalidPositionShouldThrow() {
        ArrayList<Integer> list = new ArrayList<>();

        list.get(5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void RemoveAtInvalidPositionShouldThrow() {
        ArrayList<Integer> list = new ArrayList<>();

        list.removeAt(3);
    }
}
