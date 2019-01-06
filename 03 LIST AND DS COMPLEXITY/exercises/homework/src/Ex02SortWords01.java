import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex02SortWords01 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] words = reader.readLine().split("\\s+");

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].compareTo(words[j]) > 0) {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }

        String concatenated = Arrays.stream(words).reduce("", (w1, w2) -> w1 + " " + w2);
        System.out.println(concatenated);

//        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < words.length; i++) {
//            output.append(words[i]).append(" ");
//        }
//        System.out.println(output.toString().trim());
    }

}
