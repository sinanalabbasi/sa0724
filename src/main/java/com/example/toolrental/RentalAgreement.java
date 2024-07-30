package com.example.toolrental;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * The RentalAgreement class represents the details of a rental agreement between the store and the customer.
 * It includes attributes like tool details, rental days, checkout date, due date, charges, and discount information.
 */
public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private int discountPercent;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    /**
     * Constructor to initialize the rental agreement with the provided tool, rental days, discount percent, and checkout date.
     * It also calculates the due date, chargeable days, pre-discount charge, discount amount, and final charge.
     */
    public RentalAgreement(Tool tool, int rentalDays, int discountPercent, LocalDate checkoutDate) throws IllegalArgumentException {
        if (rentalDays < 1) throw new IllegalArgumentException("Rental days must be 1 or greater.");
        if (discountPercent < 0 || discountPercent > 100) throw new IllegalArgumentException("Discount percent must be between 0 and 100.");

        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.dailyRentalCharge = tool.getDailyCharge();
        this.chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
        this.preDiscountCharge = calculatePreDiscountCharge();
        this.discountAmount = calculateDiscountAmount();
        this.finalCharge = calculateFinalCharge();
    }

    // Calculate the chargeable days excluding weekends and holidays based on tool attributes
    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
        int chargeDays = 0; // Initialize the count of chargeable days to 0
        LocalDate currentDate = checkoutDate.plusDays(1); // Start counting from the day after checkout

        // Loop through each day from the day after checkout to the due date
        while (!currentDate.isAfter(dueDate)) {
            // Determine if the current date is a weekend
            boolean isWeekend = (currentDate.getDayOfWeek().getValue() == 6 || currentDate.getDayOfWeek().getValue() == 7);

            // Determine if the current date is a holiday
            boolean isHoliday = isHoliday(currentDate);

            // Check if the current date should be charged based on the tool's charging rules
            if ((tool.isWeekdayCharge() && !isWeekend && !isHoliday) || // Charge on weekdays that are not holidays
                    (tool.isWeekendCharge() && isWeekend && !isHoliday) || // Charge on weekends that are not holidays
                    (tool.isHolidayCharge() && isHoliday)) { // Charge on holidays
                chargeDays++; // Increment the count of chargeable days
            }

            // Move to the next day
            currentDate = currentDate.plusDays(1);
        }

        // Return the total count of chargeable days
        return chargeDays;
    }

    // Check if a date is a holiday (Independence Day or Labor Day)
    private boolean isHoliday(LocalDate date) {
        int month = date.getMonthValue(); // Get the month value (1-12) of the date
        int day = date.getDayOfMonth(); // Get the day of the month (1-31) of the date
        int dayOfWeek = date.getDayOfWeek().getValue(); // Get the day of the week (1=Monday, ..., 7=Sunday) of the date

        // Check for Independence Day (July 4th)
        if (month == 7) { // If the month is July
            if (day == 4) { // If the day is the 4th of July
                return true; // It's Independence Day
            } else if ((day == 3 && dayOfWeek == 5) || (day == 5 && dayOfWeek == 1)) {
                // If the day is July 3rd and it's a Friday, or the day is July 5th and it's a Monday
                // These are the observed dates if July 4th falls on a weekend
                return true; // It's an observed Independence Day
            }
        }

        // Check for Labor Day (first Monday in September)
        if (month == 9 && dayOfWeek == 1 && day <= 7) {
            // If the month is September, the day is a Monday (dayOfWeek == 1),
            // and the day of the month is between 1 and 7 (inclusive)
            return true; // It's Labor Day
        }

        return false; // If none of the above conditions are met, the date is not a holiday
    }

    // Calculate the pre-discount charge
    private double calculatePreDiscountCharge() {
        return chargeDays * dailyRentalCharge;
    }

    // Calculate the discount amount
    private double calculateDiscountAmount() {
        return preDiscountCharge * discountPercent / 100.0;
    }

    // Calculate the final charge after applying the discount
    private double calculateFinalCharge() {
        return preDiscountCharge - discountAmount;
    }

    // Print the rental agreement details to the console
    public void printAgreement() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy"); // Formatter for the date
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US); // Formatter for the currency

        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: " + currencyFormatter.format(dailyRentalCharge));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormatter.format(preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: " + currencyFormatter.format(discountAmount));
        System.out.println("Final charge: " + currencyFormatter.format(finalCharge));
        System.out.println("------------------------------------------------------------------------");
    }
}
