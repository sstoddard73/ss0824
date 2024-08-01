class PriceRules
{
    // Data fields.

    private String toolType;
    private int dailyCharge;       // whole number of cents, to avoid floating point rounding errors
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    // Constructors.

    public PriceRules() {
        toolType = "";
        dailyCharge = 0;
        weekdayCharge = false;
        weekendCharge = false;
        holidayCharge = false;
    }

    public PriceRules(String toolType, int dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        setToolType(toolType);
        setDailyCharge(dailyCharge);
        setWeekdayCharge(weekdayCharge);
        setWeekendCharge(weekendCharge);
        setHolidayCharge(holidayCharge);
    }

    // Getters and setters.

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public int getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(int dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    // Serialize/stringify methods.

    @Override
    public String toString() {
        // Preformat some of the values.
        String dailyChargeString = "$%d.%02d".formatted(dailyCharge / 100, dailyCharge % 100);
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