import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Ex04RemoveOddOccurrences {
    private static BufferedReader reader;
    private static List<Integer> numbers;
    private static Map<Integer, Integer> occurrences;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        occurrences = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        countOccurrences();
        System.out.println(getResultString());
    }

    private static String getResultString() {
        StringBuilder output = new StringBuilder();
        numbers.forEach(n -> {
            if (occurrences.get(n) % 2 == 0) {
                output.append(n).append(" ");
            }
        });
        return output.toString();
    }

    private static void countOccurrences() throws IOException {
        numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .peek(n -> {
                    occurrences.putIfAbsent(n, 0);
                    int updateCount = occurrences.get(n) + 1;
                    occurrences.put(n, updateCount);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
