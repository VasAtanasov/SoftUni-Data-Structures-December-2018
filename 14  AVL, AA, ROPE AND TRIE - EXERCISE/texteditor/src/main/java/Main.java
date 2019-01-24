import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader;
    private static TextEditor editor;

    static {
        editor = new TextEditorImpl();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! Constants.END_COMMAND.equals(input = reader.readLine())) {
            if (input.startsWith(Constants.LOGIN)) {
                editor.login(getUsername(input));
            } else if (input.startsWith(Constants.LOGOUT)) {
                editor.logout(getUsername(input));
            } else if (input.startsWith(Constants.USERS)) {
//                List<String> users = editor.users(getPrefix(input));
            } else {
                performUserCommand(input);
            }
        }
    }

    private static void performUserCommand(String input) {
        String[] tokens = getTokens(input);
        String username = tokens[0];
        Command command = Command.valueOf(tokens[1].toUpperCase());

        switch (command) {
            case INSERT:
                editor.insert(username, Integer.parseInt(tokens[2]), getStringInQuotse(input));
                break;
            case PREPEND:
                editor.prepend(username, getStringInQuotse(input));
                break;
            case SUBSTRING:
                editor.substring(username, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                break;
            case DELETE:
                editor.delete(username, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                break;
            case CLEAR:
                editor.clear(username);
                break;
            case LENGTH:
                editor.length(username);
                break;
            case PRINT:
                editor.print(username);
                break;
            case UNDO:
                editor.undo(username);
                break;
        }
    }

    private static String getPrefix(String input) {
        String[] tokens = getTokens(input);
        return tokens.length == 1 ? null : tokens[1];
    }

    private static String getUsername(String input) {
        return getTokens(input)[0];
    }

    private static String[] getTokens(String input) {
        return input.split("\\s+");
    }

    private static String getStringInQuotse(String input) {
        return input.substring(input.indexOf("\"")).replaceAll("[\"]", "");
    }

}
