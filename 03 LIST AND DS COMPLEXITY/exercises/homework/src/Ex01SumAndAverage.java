import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex01SumAndAverage {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        List<Integer> list = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int sum;
        double average;

        if (list.size() > 0) {
            sum = list.stream().reduce((a, b) -> a + b).orElse(0);
            average = (double) sum / list.size();
        } else {
            sum = 0;
            average = 0;
        }

        String output = String.format("Sum=%d; Average=%.2f", sum, average);
        System.out.println(output);
    }
}
