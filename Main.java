import exceptions.InventoryException;
import model.Price;
import model.Product;
import model.Sku;
import repository.ProductRepository;
import repository.ProductRepositoryImp;

public class Main {
    public static void main(String[] args) {
       ProductRepository inventory = new ProductRepositoryImp();

        System.out.println("===  GESTION DES STOCKS DAKARDROP (JAVA RECORDS & DDD) ===");

        Sku skuGadget = new Sku("dkrgadget99");
        Price prix1 = new Price(12500);

        Product product1 = new Product(skuGadget, "Ring Light Pro", "Pour les créateurs de contenu TikTok", prix1, 3, 5);
        inventory.save(product1);

        System.out.println("Produit enregistré : " + product1.getName());
        System.out.println("SKU Formaté : " + product1.getSku().value());
        System.out.println("Prix de vente : " + product1.getPrice().amount() + " " + product1.getPrice().currency());

        System.out.println("\n---  Vérification des alertes logistiques ---");
        inventory.findLowStockProducts().forEach(p -> {
            System.out.println(" REAPPROVISIONNEMENT REQUIS : " + p.getName() + " (Stock actuel: " + p.getStockQuantity() + ")");
        });

        System.out.println("\n---  Tentative de livraison urgente de 5 Ring Lights ---");
        try {
            product1.decreaseStock(5);
        } catch (InventoryException e) {
            System.err.println(" Erreur interceptée avec succès : " + e.getMessage());
        }

        System.out.println("\n---  Tentative de création d'un prix frauduleux ---");
        try {
            Price prixInvalide = new Price(-5000, "XOF");
        } catch (InventoryException e) {
            System.err.println(" Blocage du prix négatif : " + e.getMessage());
        }
    }
}
