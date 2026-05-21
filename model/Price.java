package model;

import exceptions.InventoryException;

public record Price(double amount, String currency) {

    public Price(double amount) {
        this(amount, "XOF");
    }

    public Price {
        if (amount < 0) {   
            throw new InventoryException("Le prix ne peut pas être négatif.");
        }
        if (currency == null || !currency.equals("XOF")) {
            throw new InventoryException("DakarDrop fonctionne exclusivement en Francs CFA (XOF).");
        }
    }
}
