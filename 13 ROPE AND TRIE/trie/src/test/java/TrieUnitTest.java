import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrieUnitTest {

    @Test
    public void insert_Contains_Single_NoMatches() {
        Trie<Integer> trie = new Trie<>();

        trie.insert("Andy", 25);

        Assert.assertFalse(trie.contains("A"));
    }

    @Test
    public void insert_Contains_Single_Match() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);

        Assert.assertTrue(trie.contains("Andy"));
    }

    @Test
    public void insert_Contains_Multiple() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("A", 25);
        trie.insert("AA", 25);
        trie.insert("AAA", 25);

        Assert.assertTrue(trie.contains("A"));
        Assert.assertTrue(trie.contains("AA"));
        Assert.assertTrue(trie.contains("AAA"));
    }

    @Test
    public void getByPrefix_SingleCharPrefix_NoMatches() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);

        String[] expected = new String[] {};
        Iterable<String> res = trie.getByPrefix("Z");
        List<String> list = new ArrayList<>();

        res.iterator().forEachRemaining(list::add);

        Assert.assertArrayEquals(expected, list.toArray());
    }


    @Test
    public void getByPrefix_SingleCharPrefix_SingleMatch() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);

        String[] expected = new String[] {"Andy"};
        Iterable<String> res = trie.getByPrefix("A");
        List<String> list = new ArrayList<>();

        res.iterator().forEachRemaining(list::add);

        Assert.assertArrayEquals(expected, list.toArray());
    }


    @Test
    public void getByPrefix_SingleCharPrefix_MultipleMatches() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);
        trie.insert("Amy", 8);
        trie.insert("Adam", 12);
        trie.insert("Bill", 13);

        String[] expected = new String[] {"Andy", "Amy", "Adam"};
        Iterable<String> res = trie.getByPrefix("A");
        List<String> list = new ArrayList<>();

        res.iterator().forEachRemaining(list::add);

        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void getByPrefix_TwoCharPrefix_SingleMatch() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);

        String[] expected = new String[] {"Andy"};
        Iterable<String> res = trie.getByPrefix("An");
        List<String> list = new ArrayList<>();

        res.iterator().forEachRemaining(list::add);

        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void getByPrefix_TwoCharPrefix_MultipleMatches() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);
        trie.insert("Andy M", 8);
        trie.insert("Adam", 12);
        trie.insert("Bill", 13);

        String[] expected = new String[] {"Andy", "Andy M"};
        Iterable<String> res = trie.getByPrefix("An");
        List<String> list = new ArrayList<>();

        res.iterator().forEachRemaining(list::add);

        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void getValue_Single() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);

        Assert.assertEquals(Integer.valueOf(25), trie.getValue("Andy"));
    }

    @Test
    public void getValue_Multiple() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);
        trie.insert("Andy M", 8);
        trie.insert("Adam", 12);
        trie.insert("Bill", 13);

        int[] expected = new int[] {25, 8, 12, 13};

        Iterable<String> res = trie.getByPrefix("");
        List<String> actual = new ArrayList<>();
        res.iterator().forEachRemaining(actual::add);

        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(Integer.valueOf(expected[i]), trie.getValue(actual.get(i)));
        }
    }

    @Test
    public void deleteWord_Single() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);
        trie.insert("Andy M", 8);
        trie.insert("Adam", 12);
        trie.insert("Bill", 13);
        trie.delete("Andy");

        int[] expected = new int[] {8, 12, 13};

        Iterable<String> res = trie.getByPrefix("");
        List<String> actual = new ArrayList<>();
        res.iterator().forEachRemaining(actual::add);

        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(Integer.valueOf(expected[i]), trie.getValue(actual.get(i)));
        }
    }

    @Test
    public void deleteWord_Multiple() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("Andy", 25);
        trie.insert("Andy M", 8);
        trie.insert("Adam", 12);
        trie.insert("Bill", 13);

        trie.delete("Andy M");
        trie.delete("Andy");
        trie.delete("Bill");

        int[] expected = new int[] {12};

        Iterable<String> res = trie.getByPrefix("");
        List<String> actual = new ArrayList<>();
        res.iterator().forEachRemaining(actual::add);

        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(Integer.valueOf(expected[i]), trie.getValue(actual.get(i)));
        }
    }
}
