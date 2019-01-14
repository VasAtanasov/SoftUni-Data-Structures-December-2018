import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private T value;
    private List<Tree<T>> children;
    private Tree<T> paren;

    public Tree() {
        this.children = new ArrayList<>();
    }

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this();
        this.value = value;
        for (Tree<T> child : children) {
            this.children.add(child);
            child.paren = this;
        }
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Tree<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    public Tree<T> getParen() {
        return this.paren;
    }

    public void setParen(Tree<T> paren) {
        this.paren = paren;
    }
}
