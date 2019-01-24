import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextEditorImpl implements TextEditor {

    private Trie<List<String>> users;
    private Map<String, Deque<List<String>>> cash;

    public TextEditorImpl() {
        this.users = new Trie<>();
        this.cash = new HashMap<>();
    }


    @Override

    public void login(String username) {

    }

    @Override
    public void logout(String username) {

    }

    @Override
    public void prepend(String username, String string) {

    }

    @Override
    public void insert(String username, int index, String string) {

    }

    @Override
    public void substring(String username, int startIndex, int length) {

    }

    @Override
    public void delete(String username, int startIndex, int length) {

    }

    @Override
    public void clear(String username) {

    }

    @Override
    public int length(String username) {
        return 0;
    }

    @Override
    public String print(String username) {
        return null;
    }

    @Override
    public void undo(String username) {

    }

    @Override
    public Iterable<String> users(String prefix) {
        return null;
    }
}
