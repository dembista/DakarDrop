package model;

import java.util.Objects;

import exceptions.InventoryException;

public class Product {
    private final Sku sku;
    private String name;
    private String description;
    private Price price;
    private int stockQuantity;
    private final int alertThreshold;

    public Product(Sku sku, String name, String description, Price price, int initialStock, int alertThreshold) {
        this.sku = Objects.requireNonNull(sku, "Le SKU est obligatoire.");
        setName(name);
        this.description = description;
        setPrice(price);

        if (initialStock < 0) {
            throw new InventoryException("Le stock initial ne peut pas être négatif.");
        }
        if (alertThreshold < 0) {
            throw new InventoryException("Le seuil d'alerte ne peut pas être négatif.");
        }
        this.stockQuantity = initialStock;
        this.alertThreshold = alertThreshold;
    }


    public void replenishStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité de réapprovisionnement doit être supérieure à 0.");
        }
        this.stockQuantity += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité à retirer doit être supérieure à 0.");
        }
        if (this.stockQuantity - quantity < 0) {
            throw new InventoryException(
                "Opération refusée : Stock insuffisant pour " + sku.value() +
                " (Disponible: " + this.stockQuantity + ", Demandé: " + quantity + ")"
            );
        }
        this.stockQuantity -= quantity;
    }

    public boolean isStockLow() {
        return this.stockQuantity <= this.alertThreshold;
    }

    public void setName(String name) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Le nom du produit ne peut pas être vide.");
        }
        this.name = name.strip();
    }

    public void setPrice(Price price) {
        this.price = Objects.requireNonNull(price, "Le prix est obligatoire.");
    }
    public Sku getSku() { return sku; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Price getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public int getAlertThreshold() { return alertThreshold; }
}
