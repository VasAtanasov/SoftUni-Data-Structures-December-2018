import java.util.*;

public class TextEditorImpl implements TextEditor {

    private Trie<List<Character>> users;
    private Trie<Deque<String>> cash;

    public TextEditorImpl() {
        this.users = new Trie<>();
        this.cash = new Trie<>();
    }


    @Override
    public void login(String username) {
        this.users.insert(username, new ArrayList<>());
        this.cash.insert(username, new ArrayDeque<>());
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
