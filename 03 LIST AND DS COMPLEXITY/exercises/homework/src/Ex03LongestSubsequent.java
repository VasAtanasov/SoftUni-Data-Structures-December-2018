import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex03LongestSubsequent {
    private static BufferedReader reader;
    private static List<Integer> numbers;
    private static int bestLength;
    private static int bestLengthIndex;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        bestLength = 1;
        bestLengthIndex = 0;
//        numbers = Arrays.asList(12, 2, 7, 4, 3, 3, 8);
//        numbers = Arrays.asList(2, 2, 2, 3, 3, 3);
    }

    public static void main(String[] args) throws IOException {
        readInput();
        int currentLength = 1;
        int lastIndex = 0;

        for (int left = 0, right = left + 1; left < numbers.size() - 1 && right < numbers.size(); left++, right++) {
            int leftElements = numbers.get(left);
            int rightElement = numbers.get(right);

            if (leftElements == rightElement) {
                currentLength++;
                lastIndex = right;
            } else if (currentLength > bestLength) {
                bestLength = currentLength;
                bestLengthIndex = lastIndex - (currentLength - 1);
                currentLength = 1;
                lastIndex = right;
            }

            if (right == numbers.size() - 1 && currentLength > bestLength) {
                bestLength = currentLength;
                bestLengthIndex = lastIndex - (currentLength - 1);
            }
        }

        System.out.println(resultAsString());
    }

    private static String resultAsString() {
        StringBuilder output = new StringBuilder();
        for (int i = bestLengthIndex; i < bestLengthIndex + bestLength; i++) {
            output.append(numbers.get(i)).append(" ");
        }
        return output.toString().trim();
    }

    private static void readInput() throws IOException {
        numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
