import java.util.*; 
import java.math.BigDecimal; 
import java.text.NumberFormat;

class Tool
{
    // Data fields.

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int dailyCharge;       // whole number of cents, to avoid floating point rounding errors
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    // Constructors.

    public Tool() {
        toolCode = "";
        toolType = "";
        toolBrand = "";
        dailyCharge = 0;
        weekdayCharge = false;
        weekendCharge = false;
        holidayCharge = false;
    }

    public Tool(String toolCode, String toolType, String toolBrand, int dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        setToolCode(toolCode);
        setToolType(toolType);
        setToolBrand(toolBrand);
        setDailyCharge(dailyCharge);
        setWeekdayCharge(weekdayCharge);
        setWeekendCharge(weekendCharge);
        setHolidayCharge(holidayCharge);
    }

    // Getters and setters.

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
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
        String formatString = "               Tool: %s\n"
                            + "               Type: %s\n"
                            + "              Brand: %s\n"
                            + "       Daily Charge: %s\n"
                            + "  Charge on Weekday? %s\n"
                            + "  Charge on Weekend? %s\n"
                            + "  Charge on Holiday? %s\n";
        return formatString.formatted(toolCode, toolType, toolBrand, dailyChargeString, weekdayString, weekendString, holidayString);
    }
};