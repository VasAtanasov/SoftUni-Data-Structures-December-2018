import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex03LongestSubSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        int count = 1;
        int number = numbers.get(0);
        int maxCount = 1;

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).equals(numbers.get(i - 1))) {
                count++;
                if (i == numbers.size() - 1 && count > maxCount) {
                    maxCount = count;
                    number = numbers.get(i);
                }
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    number = numbers.get(i - 1);
                }
                count = 1;
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < maxCount; i++) {
            output.append(number).append(" ");
        }
        System.out.println(output.toString().trim());

    }
}
