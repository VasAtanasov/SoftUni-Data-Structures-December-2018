import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex05CountOccurrences01 {
    private static BufferedReader reader;
    private static List<Integer> number;
    private static int[] occurrences;
    private static int maxElement;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        number = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();
        countOccurrences();
        System.out.println(getResultString());
    }

    private static void countOccurrences() {
        for (Integer number : number) {
            occurrences[number]++;
        }
    }

    private static String getResultString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] > 0) {
                output.append(String.format("%d -> %d times%n", i, occurrences[i]));
            }
        }
        return output.toString();
    }

    private static void readInput() throws IOException {
        maxElement = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .peek(number -> Ex05CountOccurrences01.number.add(number))
                .max(Integer::compareTo).get();

        occurrences = new int[maxElement + 1];
    }
}
