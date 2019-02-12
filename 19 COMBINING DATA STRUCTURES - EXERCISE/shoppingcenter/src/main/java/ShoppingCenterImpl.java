import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.TreeMultimap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShoppingCenterImpl implements ShoppingCenter {

    private Multimap<String, Product> byProducer;
    private Multimap<Double, Product> byPrice;
    private Multimap<String, Product> byName;

    public ShoppingCenterImpl() {
        this.byProducer = ArrayListMultimap.create();
        this.byPrice = ArrayListMultimap.create();
        this.byName = TreeMultimap.create(Comparator.naturalOrder(), Comparator.comparing(Product::getName));
    }

    @Override
    public void addProduct(Product product) {
        this.addByName(product);
        this.addByPrice(product);
        this.addByProducerProduct(product);
    }

    private void addByName(Product product) {
        this.byName.put(product.getName(), product);
    }

    private void addByPrice(Product product) {
        this.byPrice.put(product.getPrice(), product);
    }

    private void addByProducerProduct(Product product) {
        this.byProducer.put(product.getProducer(), product);
    }

    @Override
    public int deleteProducts(String producer) {
        if (! this.byProducer.containsKey(producer)) {
            return 0;
        }

        int count = this.byProducer.get(producer).size();

        this.byProducer = Multimaps.filterValues(this.byProducer, isProducer(producer)::test);
        this.byPrice = Multimaps.filterValues(this.byPrice, isProducer(producer)::test);
        this.byName = Multimaps.filterValues(this.byName, isProducer(producer)::test);

        return count;
    }

    private Predicate<Product> isProducer(String producer) {
        return p -> Objects.requireNonNull(p).getProducer().equals(producer);
    }

    @Override
    public long deleteProducts(String name, String producer) {
        long count = this.byProducer
                .get(producer)
                .stream()
                .filter(p -> p.getName().equals(name))
                .count();

        this.byProducer = Multimaps.filterValues(this.byProducer, isNameProducer(name, producer)::test);
        this.byPrice = Multimaps.filterValues(this.byPrice, isNameProducer(name, producer)::test);
        this.byName = Multimaps.filterValues(this.byName, isNameProducer(name, producer)::test);

        return count;
    }

    private Predicate<Product> isNameProducer(String name, String producer) {
        return p -> {
            boolean isProducer = Objects.requireNonNull(p).getProducer().equals(producer);
            boolean isName = p.getName().equals(name);
            return isName && isProducer;
        };
    }

    @Override
    public Collection<Product> findProductsByName(String name) {
        if (! this.byName.containsKey(name)) {
            return Collections.emptyList();
        }
        return this.byName.get(name)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> findProductsByProducer(String producer) {
        if (! this.byProducer.containsKey(producer)) {
            return Collections.emptyList();
        }
        return this.byProducer.get(producer)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> findProductsByPriceRange(double from, double to) {
        return this.byPrice.entries()
                .stream()
                .filter(entry -> this.inRange(from, to, entry.getKey()))
                .map(Map.Entry::getValue)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Iterator<Product> iterator() {
        return this.byProducer.values().iterator();
    }

    private boolean inRange(double start, double end, double value) {
        return start <= value && value <= end;
    }
}
