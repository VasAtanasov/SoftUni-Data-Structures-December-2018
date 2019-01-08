import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Ex06SequenceNM {
    private static BufferedReader reader;
    private static Deque<Item> deque;

    static {
        deque = new ArrayDeque<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = input[0];
        int end = input[1];

        if (end < start) {
            return;
        }

        deque.addLast(new Item(start, null));

        while (! deque.isEmpty()) {
            Item current = deque.removeFirst();

            if (current.value < end) {
                deque.addLast(new Item(current.value + 1, current));
                deque.addLast(new Item(current.value + 2, current));
                deque.addLast(new Item(current.value * 2, current));
            }

            if (current.value == end) {
                deque.clear();
                while (current != null) {
                    deque.addFirst(current);
                    current = current.previousItem;
                }

                System.out.println(
                        deque
                                .stream()
                                .map(Item::toString)
                                .collect(Collectors.joining(" -> "))
                );
                break;
            }
        }
    }

    private static class Item {
        private int value;
        private Item previousItem;

        Item(int value, Item previousItem) {
            this.value = value;
            this.previousItem = previousItem;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
