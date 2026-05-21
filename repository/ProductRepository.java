package repository;

import java.util.List;
import java.util.Optional;

import model.Product;
import model.Sku;

public interface ProductRepository {
    void save(Product product);
    Optional<Product> findBySku(Sku sku);

    
    List<Product> findLowStockProducts();
}
