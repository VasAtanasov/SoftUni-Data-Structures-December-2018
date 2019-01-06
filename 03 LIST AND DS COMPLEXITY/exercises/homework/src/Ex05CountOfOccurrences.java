import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex05CountOfOccurrences {

    private static BufferedReader reader;
    private static final String[] inputs;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        inputs = new String[]{
                "3 4 4 2 3 3 4 3 2",
                "1000",
                "0 0 0",
                "7 6 5 5 6",
        };
    }


    public static void main(String[] args) throws IOException {
        run(reader.readLine());
//        test();
    }

    private static void test() throws IOException {
        for (String input : inputs) {
            run(input);
            System.out.println();
        }
    }

    private static void run(String input) {
        List<Integer> integers = Arrays.stream(input.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int max = getMax(integers);

        int[] counts = new int[max + 1];

        for (int i = 0; i <= max; i++) {
            int count = 0;
            for (Integer integer : integers) {
                if (i == integer) {
                    count++;
                }
            }
            counts[i] = count;
        }

        List<String> output = new ArrayList<>();

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                output.add(String.format("%d -> %d times", i, counts[i]));
            }
        }

        System.out.println(String.join("\n", output));
    }

    private static int getMax(List<Integer> integers) {
        int max = 0;
        for (Integer integer : integers) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }
}
