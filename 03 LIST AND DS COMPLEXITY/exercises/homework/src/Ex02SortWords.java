import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex02SortWords {

    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        List<String> list = Arrays.stream(reader.readLine().split("\\s+"))
                .sorted()
                .collect(Collectors.toList());

        System.out.println(String.join(" ",list));
    }
}
