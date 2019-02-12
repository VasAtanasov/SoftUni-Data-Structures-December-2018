import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Main {
    private static BufferedReader reader;
    private static ShoppingCenter center;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        center = new ShoppingCenterImpl();
    }

    public static void main(String[] args) throws IOException {
        readInput();

        String debug = "";
    }

    private static void readInput() throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {

            String line = reader.readLine();

            Command command = Command.valueOf(line.substring(0, line.indexOf(" ")));
            String[] tokens = line.substring(line.indexOf(" ") + 1).split(";");

            switch (command) {
                case AddProduct:
                    addProduct(tokens);
                    break;
                case DeleteProducts:
                    deleteProducts(tokens);
                    break;
                case FindProductsByName:
                    findProductsByName(tokens[0]);
                    break;
                case FindProductsByProducer:
                    findProductsByProducer(tokens[0]);
                    break;
                case FindProductsByPriceRange:
                    findProductsByPriceRange(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
                    break;
            }
        }
    }

    private static void deleteProducts(String[] params) {
        long count;

        if (params.length == 1) {
            count = center.deleteProducts(params[0]);
        } else {
            count = center.deleteProducts(params[0], params[1]);
        }

        if (count == 0) {
            System.out.println("No products found");
        } else {
            System.out.println(String.format("%d products deleted", count));
        }
    }

    private static void findProductsByPriceRange(double from, double to) {
        Collection<Product> products = center.findProductsByPriceRange(from, to);

        if (products.isEmpty()) {
            System.out.println("No products found");
            return;
        }

        products.forEach(System.out::println);
    }

    private static void findProductsByProducer(String producer) {
        Collection<Product> products = center.findProductsByProducer(producer);

        if (products.isEmpty()) {
            System.out.println("No products found");
            return;
        }

        products.forEach(System.out::println);
    }

    private static void findProductsByName(String name) {
        Collection<Product> products = center.findProductsByName(name);

        if (products.isEmpty()) {
            System.out.println("No products found");
            return;
        }

        products.forEach(System.out::println);
    }

    private static void addProduct(String[] tokens) {
        Product product = new Product(tokens[0], Double.parseDouble(tokens[1]), tokens[2]);
        center.addProduct(product);
        System.out.println("Product added");
    }
}
