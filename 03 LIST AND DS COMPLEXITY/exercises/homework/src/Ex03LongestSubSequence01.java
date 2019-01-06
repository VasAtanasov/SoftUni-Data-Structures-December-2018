import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex03LongestSubSequence01 {

    private static BufferedReader reader;
    private static final String[] inputs;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        inputs = new String[]{
                "2 2 2 3 3 3",
                "12 2 7 4 3 3 8",
                "4 4 5 5 5",
                "1 2 3",
                "0",
                "4 2 3 4 4"
        };
    }

    public static void main(String[] args) throws IOException {
        run(reader.readLine());
//        test();

    }

    private static void test() {
        for (String input : inputs) {
            run(input);
            System.out.println();
        }
    }

    private static void run(String input) {
        List<Integer> integers = Arrays.stream(input.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(output(findSubSequence(integers)));
    }

    private static List<Integer> findSubSequence(List<Integer> list) {
        int bestStart = 0;
        int bestCount = 1;

        int currentCount = 1;

        for (int i = 0; i < list.size() - 1; i++) {
            int current = list.get(i);
            int next = list.get(i + 1);

            if (current == next) {
                currentCount++;
                if (i == list.size() - 2 && currentCount > bestCount) {
                    bestCount = currentCount;
                    bestStart = i - (bestCount - 2);
                }
            } else if (currentCount > bestCount) {
                bestCount = currentCount;
                currentCount = 1;
                bestStart = i - (bestCount - 1);
            }

        }

        return list.subList(bestStart, bestStart + bestCount);
    }

    private static <T> String output(List<T> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}