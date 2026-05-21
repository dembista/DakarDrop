package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Product;
import model.Sku;

public class ProductRepositoryImp implements ProductRepository {
private final Map<Sku, Product> storage = new HashMap<>();
    @Override
    public void save(Product product) {
        storage.put(product.getSku(), product);
    }

    @Override
    public Optional<Product> findBySku(Sku sku) {
       return Optional.ofNullable(storage.get(sku));
    }

    @Override
    public List<Product> findLowStockProducts() {
      return storage.values().stream()
                .filter(Product::isStockLow)
                .toList();
    }

}
