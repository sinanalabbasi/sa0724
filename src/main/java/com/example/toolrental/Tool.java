package com.example.toolrental;

import java.math.BigDecimal;

/**
 * The Tool class represents a tool available for rental.
 * Each tool has a unique code, type, brand, daily charge, and flags for weekday, weekend, and holiday charges.
 */
public class Tool {
    private String toolCode;
    private String toolType;
    private String brand;
    private BigDecimal dailyCharge;  // Usage of BigDecimal instead of double for monetary calculations to ensure precision and accuracy.
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    // Constructor to initialize the tool with given attributes
    public Tool(String toolCode, String toolType, String brand, BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    // Getters for the tool attributes
    public String getToolCode() { return toolCode; }
    public String getToolType() { return toolType; }
    public String getBrand() { return brand; }
    public BigDecimal getDailyCharge() { return dailyCharge; }
    public boolean isWeekdayCharge() { return weekdayCharge; }
    public boolean isWeekendCharge() { return weekendCharge; }
    public boolean isHolidayCharge() { return holidayCharge; }
}
