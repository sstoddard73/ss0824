import java.text.NumberFormat;
import java.util.Locale;

/**
 * PriceRules is a POJO representing a tool type and the pricing rules that apply to it.
 * The supported pricing rules are simple:  a single daily charge amount is supported,
 * and booleans indicating whether that charge applies to weekdays, weekends, and/or holidays.
 */
class PriceRules {
    private String toolType;
    private int dailyCharge;       // whole number of cents, to avoid floating point rounding errors
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    /**
     * Construct a new PriceRules object with "blank" values.
     */
    public PriceRules() {
        toolType = "";
        dailyCharge = 0;
        weekdayCharge = false;
        weekendCharge = false;
        holidayCharge = false;
    }

    /**
     * Construct a new PriceRules object with the given values.
     * @param toolType - string representing the tool type
     * @param dailyCharge - the daily charge amount as a whole number of cents
     * @param weekdayCharge - boolean indicating if the daily charge applies to non-holiday weekdays
     * @param weekendCharge - boolean indicating if the daily charge applies to non-holiday weekend days
     * @param holidayCharge - boolean indicating if the daily charge applies to holidays
     */
    public PriceRules(String toolType, int dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        setToolType(toolType);
        setDailyCharge(dailyCharge);
        setWeekdayCharge(weekdayCharge);
        setWeekendCharge(weekendCharge);
        setHolidayCharge(holidayCharge);
    }

    /**
     * Copy constructor.
     */
    public PriceRules(PriceRules rules) {
        setToolType(rules.toolType);
        setDailyCharge(rules.dailyCharge);
        setWeekdayCharge(rules.weekdayCharge);
        setWeekendCharge(rules.weekendCharge);
        setHolidayCharge(rules.holidayCharge);
    }

    /**
     * Get the tool type.
     * @return the tool type
     */
    public String getToolType() {
        return toolType;
    }

    /**
     * Set the tool type.
     * @param toolType
     */
    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    /**
     * Get the daily charge amount.
     * @return the daily charge amount
     */
    public int getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Set the daily charge amount.
     * @param dailyCharge - non-negative integer representing the whole number of cents
     */
    public void setDailyCharge(int dailyCharge) {
        if (dailyCharge < 0) {
            throw new IllegalArgumentException("dailyCharge cannot be negative");
        }
        this.dailyCharge = dailyCharge;
    }

    /**
     * Get whether or not the daily charge applies to non-holiday weekdays.
     * @return boolean
     */
    public boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    /**
     * Set whether or not the daily charge applies to non-holiday weekdays.
     * @param weekdayCharge
     */
    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    /**
     * Get whether or not the daily charge applies to non-holiday weekend days.
     * @return boolean
     */
    public boolean getWeekendCharge() {
        return weekendCharge;
    }

    /**
     * Set whether or not the daily charge applies to non-holiday weekend days.
     * @param weekendCharge
     */
    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    /**
     * Get whether or not the daily charge applies to holidays.
     * @return boolean
     */
    public boolean getHolidayCharge() {
        return holidayCharge;
    }

    /**
     * Set whether or not the daily charge applies to holidays.
     * @param holidayCharge
     */
    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    /**
     * Return a human-readable string representing the price rules.
     * @return human-readable, multi-line string
     */
    @Override
    public String toString() {
        // Preformat some of the values.
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);     // force $ rather than using current locale's currency
        String dailyChargeString = currencyFormatter.format(.01f * (float)dailyCharge);   // multiply by .01f to convert cents to decimal dollars

        String weekdayString = weekdayCharge ? "yes" : "no";
        String weekendString = weekendCharge ? "yes" : "no";
        String holidayString = holidayCharge ? "yes" : "no";
    
        // Format the full output string.
        String formatString = "               Type: %s\n"
                            + "       Daily Charge: %s\n"
                            + "  Charge on Weekday? %s\n"
                            + "  Charge on Weekend? %s\n"
                            + "  Charge on Holiday? %s\n";
        return formatString.formatted(toolType, dailyChargeString, weekdayString, weekendString, holidayString);
    }
};