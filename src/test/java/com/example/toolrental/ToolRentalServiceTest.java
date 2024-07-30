package com.example.toolrental;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * The ToolRentalServiceTest class provides unit tests for the ToolRentalService class.
 * It includes tests for valid and invalid scenarios to ensure correct functionality.
 */
public class ToolRentalServiceTest {
    private ToolRentalService service;

    // Set up the service instance
    @Before
    public void setUp() {
        service = new ToolRentalService();
    }

    // Test for invalid rental days (less than 1)
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRentalDays() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        service.checkout("LADW", 0, 10, checkoutDate);
    }

    // Test for invalid discount percent (greater than 100)
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDiscountPercent() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        service.checkout("LADW", 5, 101, checkoutDate);
    }

    // Test for valid checkout and print the rental agreement
    @Test
    public void testValidCheckout() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement = service.checkout("LADW", 3, 10, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }

    // Test for scenario 1
    @Test(expected = IllegalArgumentException.class)
    public void testScenario1() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        RentalAgreement agreement = service.checkout("JAKR", 5, 101, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }

    // Test for scenario 2
    @Test
    public void testScenario2() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement = service.checkout("LADW", 3, 10, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }

    // Test for scenario 3
    @Test
    public void testScenario3() {
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement agreement = service.checkout("CHNS", 5, 25, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }

    // Test for scenario 4
    @Test
    public void testScenario4() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        RentalAgreement agreement = service.checkout("JAKD", 6, 0, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }

    // Test for scenario 5
    @Test
    public void testScenario5() {
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement agreement = service.checkout("JAKR", 9, 0, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }

    // Test for scenario 6
    @Test
    public void testScenario6() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement = service.checkout("JAKR", 4, 50, checkoutDate);
        assertNotNull(agreement);
        agreement.printAgreement();
    }
}
