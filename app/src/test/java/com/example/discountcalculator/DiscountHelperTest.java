package com.example.discountcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Local unit tests for {@link DiscountHelper}.
 */
public class DiscountHelperTest {

    private static final double DELTA = 0.001;

    @Test
    public void calculateFinalPrice_validInputs_returnsDiscountedPrice() {
        double result = DiscountHelper.calculateFinalPrice(200.0, 25.0);
        assertEquals(150.0, result, DELTA);
    }

    @Test
    public void calculateFinalPrice_zeroDiscount_returnsOriginalPrice() {
        double result = DiscountHelper.calculateFinalPrice(100.0, 0.0);
        assertEquals(100.0, result, DELTA);
    }

    @Test
    public void calculateSavings_validInputs_returnsSavedAmount() {
        double savings = DiscountHelper.calculateSavings(100.0, 20.0);
        assertEquals(20.0, savings, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateFinalPrice_negativePrice_throwsException() {
        DiscountHelper.calculateFinalPrice(-10.0, 10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateFinalPrice_discountOver100_throwsException() {
        DiscountHelper.calculateFinalPrice(100.0, 120.0);
    }
}
