package model;

public record Sku(String value) {
    public Sku {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Le SKU ne peut pas être vide.");
        }
        value = value.strip().toUpperCase();
        if (!value.matches("^[A-Z0-9-]+$")) {
            throw new IllegalArgumentException("Format du SKU invalide (Lettres, chiffres et tirets uniquement).");
        }
    }
}
