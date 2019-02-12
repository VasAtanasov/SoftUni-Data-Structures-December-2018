import java.util.Comparator;

public class Product implements Comparable<Product> {

    private String name;
    private double price;
    private String producer;

    public Product() {
    }

    public Product(String name, double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProducer() {
        return this.producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(Product other) {
        int index = this.getName().compareTo(other.getProducer());
        index = index != 0 ? index : this.getProducer().compareTo(other.getProducer());
        return index != 0 ? index : Double.compare(this.getPrice(), other.getPrice());
    }

    @Override
    public String toString() {
        return String.format("{%s;%s;%.2f}", this.name, this.producer, this.price);
    }
}
