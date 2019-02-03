import java.util.Objects;

public class KeyValue<K, V> {

    private K key;
    private V value;

    public KeyValue(K key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return this.combineHashCodes(this.getKey().hashCode(), this.getValue().hashCode());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        KeyValue<K, V> element = (KeyValue<K, V>) obj;
        return Objects.equals(this.getKey(), element.getKey()) && Objects.equals(this.getValue(), element.getValue());
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.getKey(), this.getValue());
    }

    private int combineHashCodes(int h1, int h2) {
        return ((h1 << 5) + h1) ^ h2;
    }
}