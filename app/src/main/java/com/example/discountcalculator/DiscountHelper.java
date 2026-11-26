package com.example.discountcalculator;

/**
 * Pure Java utility for calculating discounted prices and savings.
 */
public final class DiscountHelper {

    private DiscountHelper() {
        // Utility class
    }

    /**
     * Calculates the final price after applying a percentage discount.
     *
     * @param originalPrice   the original item price (must be >= 0)
     * @param discountPercent the discount percentage (must be between 0 and 100 inclusive)
     * @return the final price after discount
     * @throws IllegalArgumentException if price is negative or discountPercent is outside [0, 100]
     */
    public static double calculateFinalPrice(double originalPrice, double discountPercent) {
        validateInputs(originalPrice, discountPercent);
        double discountAmount = originalPrice * (discountPercent / 100.0);
        return originalPrice - discountAmount;
    }

    /**
     * Calculates the amount saved from the discount.
     *
     * @param originalPrice   the original item price (must be >= 0)
     * @param discountPercent the discount percentage (must be between 0 and 100 inclusive)
     * @return the monetary discount amount
     * @throws IllegalArgumentException if price is negative or discountPercent is outside [0, 100]
     */
    public static double calculateSavings(double originalPrice, double discountPercent) {
        validateInputs(originalPrice, discountPercent);
        return originalPrice * (discountPercent / 100.0);
    }

    private static void validateInputs(double originalPrice, double discountPercent) {
        if (Double.isNaN(originalPrice) || Double.isNaN(discountPercent)) {
            throw new IllegalArgumentException("Inputs must be valid numbers");
        }
        if (originalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
    }
}
