package com.example.toolrental;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

/**
 * The ToolRentalService class provides the functionality to manage tool rentals.
 * It stores the available tools and processes the checkout to generate rental agreements.
 */
public class ToolRentalService {
    private Map<String, Tool> tools;

    // Initialize the available tools with their attributes
    public ToolRentalService() {
        tools = new HashMap<>();
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", new BigDecimal("1.49"), true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", new BigDecimal("1.99"), true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", new BigDecimal("2.99"), true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", new BigDecimal("2.99"), true, false, false));
    }

    /**
     * Process the checkout by generating a rental agreement for the given tool code, rental days, discount percent, and checkout date.
     * Throws an IllegalArgumentException if the tool code is invalid.
     */
    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        Tool tool = tools.get(toolCode);
        if (tool == null) throw new IllegalArgumentException("Invalid tool code.");
        return new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate);
    }
}
