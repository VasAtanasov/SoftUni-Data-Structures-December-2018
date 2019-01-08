import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Ex02CalculateSequence {
    private static BufferedReader reader;
    private static Deque<Integer> sequence;
    private static List<Integer> result;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sequence = new ArrayDeque<>();
        result = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        sequence.addLast(n);

        while (result.size() < 50) {
            int current = sequence.removeFirst();

            int firstExpression = current + 1;
            int secondExpression = current * 2 + 1;
            int thirdExpression = current + 2;

            sequence.addLast(firstExpression);
            sequence.addLast(secondExpression);
            sequence.addLast(thirdExpression);

            result.add(current);
        }

        System.out.println(
                result
                        .stream()
                        .map(Object::toString)
                        .limit(50)
                        .collect(Collectors.joining(", "))
        );
    }
}
