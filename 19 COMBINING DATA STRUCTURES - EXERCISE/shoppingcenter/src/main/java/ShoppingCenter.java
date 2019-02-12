import java.util.Collection;

public interface ShoppingCenter extends Iterable<Product> {

    /**
     * - addProduct name;price;producer – adds a product by given name, price and producer.
     * If a product with the same name / producer/ price already exists,
     * the newly added product does not affect the existing ones (duplicates are allowed).
     * As a result the command prints “Product added”.
     * <p>
     * - deleteProducts producer – deletes all products matching given producer.
     * As a result the command prints “X products deleted” where X is the number
     * of deleted products or “No products found” if no such products exist.
     * <p>
     * - deleteProducts name;producer – deletes all products matching given product name and producer.
     * As a result the command prints “X products deleted” where X is the number of deleted products
     * or “No products found” if no such products exist.
     * <p>
     * - findProductsByName name – finds all products by given product name.
     * As a result the command prints a list of products in format {name;producer;price},
     * ordered by name, producer and price. Print each product on a separate line.
     * If no products exist with the specified name, the command prints “No products found”.
     * <p>
     * - findProductsByProducer producer – finds all products by given producer.
     * As a result the command prints a list of products in format {name;producer;price},
     * ordered by name, producer and price. You should print each product on a single line.
     * If no products exist by the specified producer, the command prints “No products found”.
     * <p>
     * - findProductsByPriceRange fromPrice;toPrice – finds all products whose price is greater or equal
     * than fromPrice and less or equal than toPrice. As a result the command prints a list of products in format
     * {name;producer;price}, ordered by name, producer and price. You should print each product on a separate line.
     * If no products exist within the specified price range, the command prints “No products found”.
     */

    void addProduct(Product product);

    int deleteProducts(String producer);

    long deleteProducts(String name, String producer);

    Collection<Product> findProductsByName(String name);

    Collection<Product> findProductsByProducer(String producer);

    Collection<Product> findProductsByPriceRange(double from, double to);
}
